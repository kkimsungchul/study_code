package com.nhn.servlet.service;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;
import com.nhn.http.SimpleServlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class Hello implements SimpleServlet {
    public void service(HttpRequest req, HttpResponse res){
        Writer writer = res.getWriter();
        try{
//            writer.write("com.nhn.servlet.service Hello, ");
//            writer.flush();
//            String body ="com.nhn.servlet.service.Hello.java";
            String body = new StringBuilder("<HTML>\r\n")
                    .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                    .append("</HEAD>\r\n")
                    .append("<BODY>")
                    .append("<H1>com.nhn.servlet.service.Hello.java</H1>\r\n")
                    .append("</BODY></HTML>\r\n")
                    .toString();
            sendHeader(writer,"HTTP/1.0 200 OK","text/html; charset=utf-8",body);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void sendHeader(Writer out, String responseCode, String contentType, String body)
            throws IOException {
        Date now = new Date();
        out.write(responseCode + "\r\n");
        out.write("Date: " + now + "\r\n");
        out.write("Server: JHTTP 2.0\r\n");
        out.write("Content-length: " + body.length() + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.write(body);
        out.flush();
    }
}
