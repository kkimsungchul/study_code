package com.kakaopay.assignment.rate;


import com.kakaopay.assignment.stock.main.StockService;
import com.kakaopay.assignment.stock.main.StockVO;
import com.kakaopay.assignment.stock.rate.StockRateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DisplayName("거래량 목록 테스트")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StockRateServiceTest {

    @Autowired
    StockRateService stockRateService;
    @Autowired
    StockService stockService;

    @Test
    @DisplayName("목록 출력 테스트")
    public void getStockListByRateTest(){
        String pageNum = "0";
        assertThat(stockRateService.getStockListByRate(pageNum,null,"desc").size()).isEqualTo(20);
        for(StockVO stockVO : stockRateService.getStockListByRate(pageNum,null,"desc")){
            System.out.println("## stockVolumeVO : " + stockVO.getCode() +" , " + stockVO.getName() + " , " + stockVO.getPercent() + " , " + stockVO.getNowPrice());
        }
    }

    @Test
    @DisplayName("가격 데이터 변경 테스트")
    public void viewDataChange(){
        String pageNum = "0";
        List<StockVO> stockRateList =  stockRateService.getStockListByRate(pageNum,null,"desc");
        stockService.priceDataChange();
        List<StockVO> chageStockRateList =  stockRateService.getStockListByRate(pageNum,null,"desc");

        List<StockVO> notChangeStockViewList = stockRateList.stream()
                .filter(change -> chageStockRateList.stream().anyMatch(Predicate.isEqual(change)))
                .collect(Collectors.toList());
        assertFalse(notChangeStockViewList.size() == stockRateList.size());

    }


}
