package com.sungchul.jpatest.controller;


import com.sungchul.jpatest.domain.repository.StockListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JPATestController {

    @Autowired
    StockListRepository jpaTestRepository;

    @GetMapping("/")
    public String hi() {
        return "hi";
    }

    @GetMapping("/aa")
    public String aa() {
        return jpaTestRepository.findAll().toString();
    }
}
