package com.nhn.http;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletMapping {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletMapping.class);
    private Map<String, Object> servletMap;

    public ServletMapping(String get , Writer out) {
        String[] tokens = get.split("\\s+");

        String packageName = "com.nhn.servlet";
        String method = tokens[0];
        String mappingURL = tokens[1];
        String className = mappingURL.replaceAll("/","");
        String fullMappingURL = packageName+"."+makeClassName(className);

        this.servletMap = new HashMap<>();
        addServletMapping(mappingURL, mapping(fullMappingURL));
        HttpRequest request = new HttpRequest(mappingURL);
        HttpResponse response = new HttpResponse(out);
        handleRequest(request, response);
    }

    public String makeClassName(String fullClassName){
        String [] splitClassName = fullClassName.split("\\.");
        String result="";
        if(splitClassName.length>1){
            for(int i=0;i<splitClassName.length-1;i++){
                result += splitClassName[i]+".";
            }
        }
        String className = splitClassName[splitClassName.length-1];

        className = className.toLowerCase();
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        result +=className;
        return result;
    }

    public Object mapping(String fullMappingURL) {
        try{
            Class<?> clazz = Class.forName(fullMappingURL);
             return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            // ClassNotFoundException 처리
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void addServletMapping(String url, Object servlet) {
        servletMap.put(url, servlet);
    }

    public void handleRequest(HttpRequest req, HttpResponse res) {
        String url = req.getRequestURL();
        SimpleServlet servlet = (SimpleServlet)servletMap.get(url);
        if (servlet != null) {
            servlet.service(req, res);
        } else {
            try{
                res.getWriter().write("404 - Not Found");
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

}
