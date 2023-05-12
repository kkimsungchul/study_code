package com.kakaopay.assignment.stock.volume;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volume")
public class StockVolumeController {


    @GetMapping
    public String aa(){
        return "volume";
    }
}
