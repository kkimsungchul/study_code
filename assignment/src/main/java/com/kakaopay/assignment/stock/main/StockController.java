package com.kakaopay.assignment.stock.main;

import com.kakaopay.assignment.common.ResponseAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;


@AllArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {

    StockService stockService;

    @PostMapping("/change")
    public ResponseEntity<ResponseAPI> allDataChange(@RequestBody DataChangeVO dataChangeVO){
        String result = stockService.dataChange(dataChangeVO);
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .data(result)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

    @GetMapping(value = {""})
    public ResponseEntity<ResponseAPI> initDate(){
        int a = 10/0;
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .data(stockService.initStockPriceList())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

}
