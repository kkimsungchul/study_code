package com.kakaopay.assignment.stock.up;

import com.kakaopay.assignment.common.ResponseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/up")
public class StockUpController {

    @Autowired
    StockUpService stockUpService;

    @GetMapping
    public ResponseEntity<ResponseAPI> aa(){

        ResponseAPI responseAPI = ResponseAPI.builder()
                                    .message("success")
                                    .timestamp(LocalDateTime.now())
                                    .data(stockUpService.test())
                                    .build();


        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }
}
