package com.kakaopay.assignment.stock.init;


import com.kakaopay.assignment.stock.main.DataChangeVO;
import com.kakaopay.assignment.stock.main.StockService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;


/**
 * 어플리케이션 실행 시 모든 데이터를 변경
 * */
@AllArgsConstructor
@Component
public class InitStockData implements  ApplicationListener<ContextRefreshedEvent> {

    StockService stockService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DataChangeVO dataChangeVO = DataChangeVO.builder()
                .type("all")
                .build();
        stockService.dataChange(dataChangeVO);
    }
}
