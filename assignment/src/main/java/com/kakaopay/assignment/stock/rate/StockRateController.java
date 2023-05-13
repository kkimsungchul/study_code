package com.kakaopay.assignment.stock.rate;

import com.kakaopay.assignment.common.ResponseAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/rate")
public class StockRateController {

    StockRateService stockRateService;

    @GetMapping("/up")
    public ResponseEntity<ResponseAPI> getStockListByRateUp(@Nullable @RequestParam String page , @Nullable @RequestParam String limit){
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockRateService.getStockListByRate(page,limit,"desc"))
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

    @GetMapping("/down")
    public ResponseEntity<ResponseAPI> getStockListByRateDown(@Nullable @RequestParam String page , @Nullable @RequestParam String limit){
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockRateService.getStockListByRate(page,limit,"asc"))
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }//
}
