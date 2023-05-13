package com.kakaopay.assignment.stock.view;

import com.kakaopay.assignment.common.ResponseAPI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/view")
public class StockViewController {


    StockViewService stockViewService;


    @GetMapping(value={"", "/{page}"})
    public ResponseEntity<ResponseAPI> getStockListByView(@Nullable @PathVariable String page){
        List<StockViewVO> stockViewList = stockViewService.getStockListByView(page);
        ResponseAPI responseAPI = ResponseAPI.builder()
                .message("success")
                .timestamp(LocalDateTime.now())
                .data(stockViewList)
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }
}
