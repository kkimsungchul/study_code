package com.kakaopay.assignment.volume;


import com.kakaopay.assignment.stock.volume.StockVolumeService;
import com.kakaopay.assignment.stock.volume.StockVolumeVO;
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


@DisplayName("거래량 목록 테스트")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StockVolumeServiceTest {

    @Autowired
    StockVolumeService stockVolumeService;

    @Test
    @DisplayName("목록 출력 테스트")
    public void getStockViewListTest(){
        String pageNum = "0";
        for(StockVolumeVO stockVolumeVO : stockVolumeService.getStockListByVolume(pageNum,null)){
            System.out.println("## stockVolumeVO : " + stockVolumeVO.getCode() +" , " + stockVolumeVO.getName() + " , " + stockVolumeVO.getVolume() + " , " + stockVolumeVO.getNowPrice());
        }
    }

    @Test
    @DisplayName("거래량 데이터 변경 테스트")
    public void viewDataChange(){
        String pageNum = "0";
        List<StockVolumeVO> stockVolumeList =  stockVolumeService.getStockListByVolume(pageNum,null);
        stockVolumeService.volumeDataChange();
        List<StockVolumeVO> changeStockVolumeList =  stockVolumeService.getStockListByVolume(pageNum,null);

        List<StockVolumeVO> notChangeStockViewList = stockVolumeList.stream()
                .filter(change -> changeStockVolumeList.stream().anyMatch(Predicate.isEqual(change)))
                .collect(Collectors.toList());
        assertFalse(notChangeStockViewList.size() == stockVolumeList.size());

    }


}
