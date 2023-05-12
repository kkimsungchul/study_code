package com.kakaopay.assignment.stock.main;

import com.kakaopay.assignment.common.ResponseAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@AllArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {

    StockService stockService;

    @GetMapping
    public ResponseEntity<ResponseAPI> aa(){

        ResponseAPI responseAPI = new ResponseAPI();
        responseAPI.setData(stockService.initStockPriceList());
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

//    @GetMapping("/test")
//    public ResponseEntity<ResponseAPI> test(){
//        ResponseAPI responseAPI = new ResponseAPI();
//        responseAPI.setData(InitStockPriceVo.initStockPriceVoList);
//        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
//    }
}
