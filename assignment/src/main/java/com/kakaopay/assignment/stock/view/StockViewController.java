package com.kakaopay.assignment.stock.view;

import com.kakaopay.assignment.common.ResponseAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "인기", description = "인기 종목 조회 API")
@AllArgsConstructor
@RestController
@RequestMapping("/view")
public class StockViewController {


    StockViewService stockViewService;

    @Operation(summary ="인기종목 조회",description = "DB에 저장되어 있는 데이터에서 조회순이 높은 순으로 정렬합니다.")
    @GetMapping
    public ResponseEntity<ResponseAPI> getStockListByView(@Nullable @RequestParam String page , @Nullable @RequestParam String limit){
        List<StockViewVO> stockViewList = stockViewService.getStockListByView(page,limit);
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockViewList)
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }
}
