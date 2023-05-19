package com.nhn.servlet;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;
import com.nhn.http.SendHeaderAndData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

public class Hello implements SimpleServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);
    public void service(HttpRequest req, HttpResponse res){
        Writer writer = res.getWriter();
            String body = new StringBuilder("<HTML>\r\n")
                    .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                    .append("</HEAD>\r\n")
                    .append("<BODY>")
                    .append("<H1>com.nhn.servlet.service.Hello.java</H1>\r\n")
                    .append("</BODY></HTML>\r\n")
                    .toString();
            SendHeaderAndData.send(writer,"HTTP/1.0 200 OK","text/html; charset=utf-8",body);
    }

}