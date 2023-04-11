package com.sungchul.shedlocktest.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String hi(){
        return "hello world";
    }
}
