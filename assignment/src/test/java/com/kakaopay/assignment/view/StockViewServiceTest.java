package com.kakaopay.assignment.view;


import com.kakaopay.assignment.common.CommonUtil;
import com.kakaopay.assignment.stock.view.StockViewService;
import com.kakaopay.assignment.stock.view.StockViewVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("인기순위 목록 테스트")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StockViewServiceTest {

    @Autowired
    StockViewService stockViewService;

    @Test
    @DisplayName("목록 출력")
    public void getStockViewListTest(){
        for(StockViewVO stockViewVO : stockViewService.getStockListByView()){
            System.out.println("## stockViewVO : " + stockViewVO.getCode() +" , " + stockViewVO.getName() + " , " + stockViewVO.getNowPrice() + " , " + stockViewVO.getInitPrice());
        }
    }

    @Test
    @DisplayName("퍼센트 계산")
    public void makePercent(){
        int nowPrice =70000;
        int initPrice = 100000;
        String percent = CommonUtil.makePercent(nowPrice,initPrice);
        assertThat(percent).isEqualTo("-30.00%");
    }

}
