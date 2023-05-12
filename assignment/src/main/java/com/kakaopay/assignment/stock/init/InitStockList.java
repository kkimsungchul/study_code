//package com.kakaopay.assignment.stock.init;
//
//import com.kakaopay.assignment.stock.main.InitStockPriceVo;
//import com.kakaopay.assignment.stock.main.StockService;
//import lombok.AllArgsConstructor;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//
///**
// * 어플리케이션 실행시 주식 초기 가격을 메모리에 저장
// * */
//@AllArgsConstructor
//@Component
//public class InitStockList implements  ApplicationListener<ContextRefreshedEvent> {
//
//    StockService stockService;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        InitStockPriceVo.initStockPriceVoList = stockService.initStockPriceList();
//
//    }
//}
