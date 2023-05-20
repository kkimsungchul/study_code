package com.nhn.servlet;

import com.nhn.http.HttpRequest;
import com.nhn.http.HttpResponse;



public interface SimpleServlet {
    void service(HttpRequest req, HttpResponse res);
}
