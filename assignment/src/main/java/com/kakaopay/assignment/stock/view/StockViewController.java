package com.kakaopay.assignment.stock.view;

import com.kakaopay.assignment.common.ResponseAPI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@AllArgsConstructor
@RestController
@RequestMapping("/view")
public class StockViewController {


    StockViewService stockViewService;

    @GetMapping
    public ResponseEntity<ResponseAPI> getStockListByView(){

        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockViewService.getStockListByView())
                .build();


        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }
}
