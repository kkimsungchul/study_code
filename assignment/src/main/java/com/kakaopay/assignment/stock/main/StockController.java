package com.kakaopay.assignment.stock.main;

import com.kakaopay.assignment.common.ResponseAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;


@Tag(name = "초기 세팅", description = "시초가 확인/데이터변경 API")
@AllArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {

    StockService stockService;

    @Operation(summary ="데이터 변경",description = "DB에 저장되어 있는 데이터를 변경합니다")
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


    @Operation(summary ="시초가 조회",description = "오늘의 시초가를 조회합니다.")
    @GetMapping(value = {""})
    public ResponseEntity<ResponseAPI> initDate(){
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .data(stockService.initStockPriceList())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

}
