package com.nhn.servlet;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;
import com.nhn.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nowtime implements SimpleServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(Nowtime.class);

    /**
     * 전달받은 객체를 사용하여 화면에 출력
     * @param req HttpRequest 객체
     * @param res HttpRequest 객체
     */
    public void service(HttpRequest req, HttpResponse res){
        String body = new StringBuilder("<HTML>\r\n")
                .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                .append("</HEAD>\r\n")
                .append("<BODY>")
                .append("<H1>")
                .append(this.getClass().getName())
                .append("</H1>\r\n")
                .append("<H2>time : ")
                .append(DateUtil.getTime("yyyy-MM-dd HH:mm:ss"))
                .append("</H2>\r\n")
                .append("<h3>")
                .append(req.getAllParameter())
                .append("</h3>")
                .append("</BODY></HTML>\r\n")
                .toString();
        res.send("HTTP/1.0 200 OK","text/html; charset=utf-8",body);
    }

}