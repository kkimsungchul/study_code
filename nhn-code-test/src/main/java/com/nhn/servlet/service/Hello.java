package com.nhn.servlet.service;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;
import com.nhn.http.SendHeaderAndData;
import com.nhn.servlet.SimpleServlet;
import com.nhn.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.time.LocalDateTime;

public class Hello implements SimpleServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);
    public void service(HttpRequest req, HttpResponse res){

        Writer writer = res.getWriter();
            String body = new StringBuilder("<HTML>\r\n")
                    .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                    .append("</HEAD>\r\n")
                    .append("<BODY>")
                    .append("<H1>com.nhn.servlet.service.Hello.java</H1>\r\n")
                    .append("<H2>time : ")
                    .append(DateUtil.getTime("yyyy-MM-dd HH:mm:ss"))
                    .append("</H2>\r\n")
                    .append("</BODY></HTML>\r\n")
                    .toString();
            SendHeaderAndData.send(writer,"HTTP/1.0 200 OK","text/html; charset=utf-8",body);
    }

}
