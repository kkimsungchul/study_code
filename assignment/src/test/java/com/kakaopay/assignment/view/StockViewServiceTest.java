package com.kakaopay.assignment.view;


import com.kakaopay.assignment.stock.view.StockViewService;
import com.kakaopay.assignment.stock.view.StockViewVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;



@DisplayName("인기순위 목록 테스트")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StockViewServiceTest {

    @Autowired
    StockViewService stockViewService;

    @Test
    @DisplayName("목록 출력 테스트")
    public void getStockViewListTest(){
        String pageNum = "0";
        for(StockViewVO stockViewVO : stockViewService.getStockListByView(pageNum,null)){
            System.out.println("## stockViewVO : " + stockViewVO.getCode() +" , " + stockViewVO.getName() + " , " + stockViewVO.getView() + " , " + stockViewVO.getNowPrice());
        }
    }

    @Test
    @DisplayName("조회수 데이터 변경 테스트")
    public void viewDataChange(){
        String pageNum = "0";
        List<StockViewVO> stockViewList =  stockViewService.getStockListByView(pageNum,null);
        stockViewService.viewDataChange();
        List<StockViewVO> changeStockViewList =  stockViewService.getStockListByView(pageNum,null);

        List<StockViewVO> notChangeStockViewList = stockViewList.stream()
                .filter(change -> changeStockViewList.stream().anyMatch(Predicate.isEqual(change)))
                .collect(Collectors.toList());
        assertFalse(notChangeStockViewList.size() == stockViewList.size());

    }


}
