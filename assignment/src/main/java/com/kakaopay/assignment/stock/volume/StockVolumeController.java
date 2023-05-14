package com.kakaopay.assignment.stock.volume;

import com.kakaopay.assignment.common.ResponseAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "거래량", description = "거래량 종목 조회 API")
@AllArgsConstructor
@RestController
@RequestMapping("/volume")
public class StockVolumeController {

    StockVolumeService stockVolumeService;

    @Operation(summary ="거래량종목 조회",description = "DB에 저장되어 있는 데이터에서 거래량이 높은 순으로 정렬합니다.")
    @GetMapping
    public ResponseEntity<ResponseAPI> getStockListByView(@Nullable @RequestParam String page , @Nullable @RequestParam String limit){
        List<StockVolumeVO> stockViewList = stockVolumeService.getStockListByVolume(page,limit);
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockViewList)
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }
}
