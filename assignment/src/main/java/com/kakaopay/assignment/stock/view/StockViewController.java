package com.kakaopay.assignment.stock.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/view")
public class StockViewController {

    @GetMapping
    public String aa(){
        return "view";
    }
}
