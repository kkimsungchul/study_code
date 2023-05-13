package com.kakaopay.assignment.main;

import com.kakaopay.assignment.common.CommonUtil;
import com.kakaopay.assignment.stock.main.DataChangeVO;
import com.kakaopay.assignment.stock.main.StockService;
import com.kakaopay.assignment.stock.main.StockVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("인기순위 목록 테스트")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StockMainTest {

    @Autowired
    StockService stockService;

    @Test
    @DisplayName("파라미터 타입별 데이터 변경 테스트")
    public void dataChangeTest(){
        DataChangeVO dataChangeVO = new DataChangeVO();
        dataChangeVO.setType("all");
        assertThat(stockService.dataChange(dataChangeVO)).isEqualTo("all 데이터가 변경되었습니다.");
        dataChangeVO.setType("view");
        assertThat(stockService.dataChange(dataChangeVO)).isEqualTo("view 데이터가 변경되었습니다.");
        dataChangeVO.setType("Price");
        assertThat(stockService.dataChange(dataChangeVO)).isEqualTo("price 데이터가 변경되었습니다.");
        dataChangeVO.setType("Volume");
        assertThat(stockService.dataChange(dataChangeVO)).isEqualTo("volume 데이터가 변경되었습니다.");
        dataChangeVO.setType("");
        assertThat(stockService.dataChange(dataChangeVO)).isEqualTo("all 데이터가 변경되었습니다.");
        dataChangeVO.setType("asdfadsfqwerwer");
        assertThat(stockService.dataChange(dataChangeVO)).isEqualTo("all 데이터가 변경되었습니다.");
        dataChangeVO.setType(null);
        assertThat(stockService.dataChange(dataChangeVO)).isEqualTo("all 데이터가 변경되었습니다.");
    }

    @Test
    @DisplayName("날짜 변경 시 데이터 INSERT 테스트")
    public void toDayDataInsertTest(){
        //stock_price테이블의 데이터 날짜를 하루 전으로 변경
        stockService.stockPriceDateChange();
        assertTrue(stockService.initStockPriceList().size()==0);
        stockService.toDayDataInsert();
        assertTrue(stockService.initStockPriceList().size()==120);
    }

}
