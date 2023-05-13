package com.kakaopay.assignment.stock.rate;

import com.kakaopay.assignment.common.ResponseAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/rate")
public class StockRateController {

    StockRateService stockRateService;

    @GetMapping(value={"/up" , "/up/{page}"})
    public ResponseEntity<ResponseAPI> getStockListByRateUp(@Nullable @PathVariable String page){
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockRateService.getStockListByRate(page,"desc"))
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

    @GetMapping(value={"/down" , "/down/{page}"})
    public ResponseEntity<ResponseAPI> getStockListByRateDown(@Nullable @PathVariable String page){
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockRateService.getStockListByRate(page,"asc"))
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }//
}
