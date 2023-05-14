package com.kakaopay.assignment.stock.rate;

import com.kakaopay.assignment.common.ResponseAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "상승/하락", description = "상승/하락 종목 조회 API")
@AllArgsConstructor
@RestController
@RequestMapping("/rate")
public class StockRateController {

    StockRateService stockRateService;

    @Operation(summary ="상승종목 조회",description = "DB에 저장되어 있는 데이터에서 상승률이 높은 순으로 정렬합니다.")
    @GetMapping("/up")
    public ResponseEntity<ResponseAPI> getStockListByRateUp(@Nullable @RequestParam String page , @Nullable @RequestParam String limit){
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockRateService.getStockListByRate(page,limit,"desc"))
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

    @Operation(summary ="하락종목 조회",description = "DB에 저장되어 있는 데이터에서 하락률이 높은 순으로 정렬합니다.")
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
