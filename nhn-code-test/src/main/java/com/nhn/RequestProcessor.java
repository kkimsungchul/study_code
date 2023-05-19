package com.nhn;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;
import com.nhn.http.SendHeaderAndData;
import com.nhn.servlet.SimpleServlet;
import com.nhn.property.ConfigVO;
import com.nhn.property.HostInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class RequestProcessor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);
    private File rootDirectory;
    private String indexFileName = "index.html";
    private Socket connection;
    private HostInfoVO hostInfoVO;



    public RequestProcessor(ConfigVO configVO ,  Socket connection) {
        LOGGER.info("client connect host  " + connection.getInetAddress().getHostName());
        this.hostInfoVO =  configVO.getHost().get(0);
        for(HostInfoVO hostInfo : configVO.getHost()){
            if(hostInfo.getHostName().equalsIgnoreCase(connection.getInetAddress().getHostName().split("\\.")[0])){
                this.hostInfoVO = hostInfo;
            }
        }
        File rootDirectory = new File(hostInfoVO.getHomeDirectory());
        if (rootDirectory.isFile()) {
            LOGGER.error("rootDirectory must be a directory, not a file");
            throw new IllegalArgumentException(
                    "rootDirectory must be a directory, not a file");
        }
        try {
            rootDirectory = rootDirectory.getCanonicalFile();
        } catch (IOException ex) {
            LOGGER.error("rootDirectory getCanonicalFile Error",ex);
        }
        this.rootDirectory = rootDirectory;
        this.connection = connection;
    }




    @Override
    public void run() {
        // for security checks
        String root = rootDirectory.getPath();
        try {
            OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
            Writer out = new OutputStreamWriter(raw);
            Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "UTF-8");
            StringBuilder requestLine = new StringBuilder();
            while (true) {
                int c = in.read();
                if (c == '\r' || c == '\n')
                    break;
                requestLine.append((char) c);
            }
            String get = requestLine.toString();
            LOGGER.info("run - connection.getRemoteSocketAddress() " + connection.getRemoteSocketAddress() + " " + get);
            servletMapping(get , out);

        } catch (Exception ex) {
            LOGGER.error("Error talking to " + connection.getRemoteSocketAddress(), ex);
        } finally {
            try {
                connection.close();
            } catch (IOException ex) {
                LOGGER.error("Connection Close Error {}",ex);
            }
        }
    }



    public void servletMapping(String get , Writer out) {
        HashMap<String, Object> servletMap = new HashMap<>();
        String[] tokens = get.split("\\s+");
        String packageName = "com.nhn.servlet";
        String method = tokens[0];
        String mappingURL = tokens[1];
        String version = tokens[2];
        String className = mappingURL.replaceAll("/","");

        if(className.contains("../")){
            HttpResponse res = new HttpResponse(out);
            errorHandler(res , "403");
            return ;
        }

        String fullMappingURL="";
        className = makeClassName(className);

        if(className==null){
            String root = rootDirectory.getPath();
            File theFile = new File(rootDirectory, indexFileName);
            try{
                if (theFile.canRead() && theFile.getCanonicalPath().startsWith(root)) {
                    List<String> fileData = Files.readAllLines(theFile.toPath());
                    StringBuilder sb = new StringBuilder();
                    for (String line : fileData) {
                        sb.append(line).append("\n");
                    }
                    if (version.startsWith("HTTP/")) { // send a MIME header
                        SendHeaderAndData.send(out,"HTTP/1.0 200 OK" , "text/html; charset=utf-8" , sb.toString());
                    }
                }
            }catch (IOException ioe){
                LOGGER.error("index file read error" , ioe);
            }
        }else{
            fullMappingURL = packageName+"."+makeClassName(className);
            if (version.startsWith("HTTP/")) {
                servletMap.put(mappingURL, classMapping(fullMappingURL));
                HttpRequest request = new HttpRequest(mappingURL);
                HttpResponse response = new HttpResponse(out);
                handleRequest(request, response , servletMap);
            }
        }
    }

    public String makeClassName(String fullClassName){
        if(fullClassName.indexOf("favicon.ico")>0){
            return null;
        }else if(fullClassName.trim().length()==0){
            return null;
        }
        String [] splitClassName = fullClassName.split("\\.");
        String result="";
        if(splitClassName.length>1){
            for(int i=0;i<splitClassName.length-1;i++){
                result += splitClassName[i]+".";
            }
        }
        String className = splitClassName[splitClassName.length-1];
        if(className.length()>0){
            className = className.toLowerCase();
            className = className.substring(0, 1).toUpperCase() + className.substring(1);
        }
        result +=className;
        return result;
    }

    public Object classMapping(String fullMappingURL) {
        try{
            Class<?> clazz = Class.forName(fullMappingURL);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException , not found mapping class " , e);
            return null;
        }catch (Exception e){
            LOGGER.error("classMapping method error " , e);
            return null;
        }

    }


    public void handleRequest(HttpRequest req, HttpResponse res , HashMap<String, Object> servletMap) {
        String url = req.getRequestURL();
        SimpleServlet servlet = (SimpleServlet)servletMap.get(url);
        if (servlet != null) {
            servlet.service(req, res);
        } else {
            errorHandler(res , "404");
        }
    }

    public void errorHandler(HttpResponse res , String errorCode){
        String fileName ="";
        String responseCode = "http/1.0 ";
        if(errorCode.equals("403")){
            fileName="403.html";
            responseCode += "403 Forbidden";
        }else if(errorCode.equals("404")){
            fileName="404.html";
            responseCode += "404 Not Found";
        }else {
            fileName="500.html";
            responseCode += "500 Server Error";
        }

        String root = rootDirectory.getPath();
        File theFile = new File(rootDirectory, fileName);
        try{
            if (theFile.canRead() && theFile.getCanonicalPath().startsWith(root)) {
                List<String> fileData = Files.readAllLines(theFile.toPath());
                StringBuilder sb = new StringBuilder();
                for (String line : fileData) {
                    sb.append(line).append("\n");
                }
                SendHeaderAndData.send(res.getWriter(),responseCode , "text/html; charset=utf-8" , sb.toString());

            }
        }catch (IOException ioe){
            LOGGER.error("index file read error" , ioe);
        }

    }

}