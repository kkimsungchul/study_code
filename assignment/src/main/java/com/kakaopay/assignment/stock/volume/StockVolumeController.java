package com.kakaopay.assignment.stock.volume;

import com.kakaopay.assignment.common.ResponseAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/volume")
public class StockVolumeController {

    StockVolumeService stockVolumeService;

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
