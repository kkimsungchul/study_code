package com.sungchul.apitest.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MainController {

    @GetMapping("/test")
    public void getTest(HttpServletRequest request){

        System.out.println("##### GET IN");


        //헤더 값 검증로직
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        System.out.println("Headers: " + headerMap);
    }

    @PostMapping("/test")
    public void postTest(HttpServletRequest request , @RequestBody Map<String, Object> dataMap){

        System.out.println("##### POST IN");

        //헤더 값 검증로직
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        System.out.println("Headers: " + headerMap);

        System.out.println(dataMap);
    }

    @GetMapping("/test2")
    public void test2(@RequestParam(value="hi") String hi){
        System.out.println(hi);

    }

    @GetMapping("/test3/{hihi}")
    public void test3(@PathVariable String hihi){
        System.out.println(hihi);

    }
}
