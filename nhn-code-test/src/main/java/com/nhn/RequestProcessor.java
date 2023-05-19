package com.nhn;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;
import com.nhn.http.ServletMapping;
import com.nhn.http.SimpleServlet;
import com.nhn.property.ConfigVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestProcessor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);
    private File rootDirectory;
    private String indexFileName = "index.html";
    private Socket connection;
    private ConfigVO.HostInfo hostInfo;



    public RequestProcessor(ConfigVO configVO , String indexFileName, Socket connection) {
        LOGGER.info("client connect host  " + connection.getInetAddress().getHostName());
        this.hostInfo =  configVO.getHost().get(0);
        for(ConfigVO.HostInfo hostInfo : configVO.getHost()){
            if(hostInfo.getHostName().equalsIgnoreCase(connection.getInetAddress().getHostName().split("\\.")[0])){
                this.hostInfo = hostInfo;
            }
        }
        File rootDirectory = new File(hostInfo.getHomeDirectory());
        if (rootDirectory.isFile()) {
            LOGGER.error("rootDirectory must be a directory, not a file");
            throw new IllegalArgumentException(
                    "rootDirectory must be a directory, not a file");
        }
        try {
            rootDirectory = rootDirectory.getCanonicalFile();
        } catch (IOException ex) {
        }
        this.rootDirectory = rootDirectory;
        if (indexFileName != null){
            this.indexFileName = indexFileName;
        }
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
            LOGGER.info(connection.getRemoteSocketAddress() + " " + get);
            callServletMapping(get , out);

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

    public void callServletMapping(String get ,Writer out ){
        new ServletMapping(get , out);
    }



}