package com.sungchul.completablefuturetestclient.client.main;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {


    @GetMapping("/{param}")
    public String test(@PathVariable("param") String param) throws Exception{
        Thread.sleep(5000);
        System.out.println("connection success ! , param : " + param);
        return "client connection : " + param;
    }

}
