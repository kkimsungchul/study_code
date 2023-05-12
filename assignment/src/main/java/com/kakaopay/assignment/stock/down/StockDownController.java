package com.kakaopay.assignment.stock.down;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/down")
public class StockDownController {

    @GetMapping
    public String aa(){
        return "down";
    }
}
