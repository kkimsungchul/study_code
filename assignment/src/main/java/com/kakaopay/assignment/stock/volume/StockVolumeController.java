package com.kakaopay.assignment.stock.volume;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/volume")
public class StockVolumeController {


    @GetMapping
    public String aa(){
        return "volume";
    }
}
