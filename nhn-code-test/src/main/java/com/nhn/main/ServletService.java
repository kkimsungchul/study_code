package com.nhn.main;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;
import com.nhn.servlet.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


/**
 * servlet 기능을 구현한 클래스
 * @author 김성철
 */
public class ServletService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletService.class);
    private String rootDirectory;
    private String indexFileName = "index.html";
    private Socket connection;

    public ServletService(){};

    public ServletService(String rootDirectory , Socket connection){
        this.rootDirectory = rootDirectory;
        this.connection = connection;
    }

    /**
     * servletMapping 전달받은 URL을 토대로 데이터를 처리하여 사용자에게 리턴
     * @param get URL 정보
     * @param out 사용자에게 데이터를 표시할 때 사용할 Writer 객체
     */
    public void servletMapping(String get , Writer out) {
        HashMap<String, Object> servletMap = new HashMap<>();
        String[] tokens = get.split("\\s+");
        String packageName = "com.nhn.servlet";
        String method = tokens[0];
        String mappingURL = tokens[1];
        String version = tokens[2];
        String queryString="";
        String className = "";
        // URL 에서 Query String 분리
        if(mappingURL.split("\\?").length>=2){
            className = mappingURL.split("\\?")[0].replaceAll("/","");
            queryString = mappingURL.split("\\?")[1];
        }else{
            className = mappingURL.replaceAll("/","");
        }

        //접근 권한이 없는 페이지 또는 .exe 파일 접근시 403 리턴
        if(mappingURL.contains("../") || mappingURL.contains(".exe")){
            HttpResponse res = new HttpResponse(out);
            errorHandler(res , "403");
            return ;
        }

        String fullMappingURL="";
        className = makeClassName(className);

        //매핑되는 class 파일이 없는경우 404페이지 리턴
        if(className==null){
            StringBuilder stringBuilder = new StringBuilder();
            String content="";
            InputStream inputStream = RequestProcessor.class.getResourceAsStream(rootDirectory+"/index.html");
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                } catch (IOException e) {
                    LOGGER.error("Index.html file Error" , e);
                }
            }
            if (version.startsWith("HTTP/")) { // send a MIME header
                HttpResponse response = new HttpResponse(out);
                response.send("HTTP/1.0 200 OK" , "text/html; charset=utf-8" , stringBuilder.toString());
            }

        }else{
            fullMappingURL = packageName+"."+makeClassName(className);
            if (version.startsWith("HTTP/")) {
                servletMap.put(mappingURL, classMapping(fullMappingURL));
                HttpRequest request = new HttpRequest(mappingURL , queryString);
                HttpResponse response = new HttpResponse(out);
                handleRequest(request, response , servletMap);
            }
        }
    }


    /**
     * URL 주소로 매핑할 클래스 이름을 생성
     * @param fullClassName 매핑할 클래스 정보
     * @return result 매핑할 클래스 이름
     */
    public String makeClassName(String fullClassName){
        if(fullClassName.indexOf("favicon")>0){
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


    /**
     * 매핑할 서블릿 클래스 생성
     * @param fullMappingURL 매핑할 클래스 이름(패키지.클래스명)
     * @return object 생성한 서블릿 클래스
     */
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

    /**
     * 클래스 메핑
     * @param req HttpRequest 객체
     * @param res HttpResponse 객체
     * @param servletMap 매핑된 서블릿 클래스 정보
     */
    public void handleRequest(HttpRequest req, HttpResponse res , HashMap<String, Object> servletMap) {
        String url = req.getRequestURL();
        SimpleServlet servlet = (SimpleServlet)servletMap.get(url);
        if (servlet != null) {
            servlet.service(req, res);
        } else {
            errorHandler(res , "404");
        }
    }

    /**
     * 오류 처리
     * @param res HttpResponse 객체
     * @param errorCode 오류코드
     */
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
        StringBuilder stringBuilder = new StringBuilder();
        String content="";
        InputStream inputStream = RequestProcessor.class.getResourceAsStream(rootDirectory+"/"+fileName);
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                LOGGER.error("Index.html file Error" , e);
            }
        }
        res.send(responseCode , "text/html; charset=utf-8" , stringBuilder.toString());
    }
}
