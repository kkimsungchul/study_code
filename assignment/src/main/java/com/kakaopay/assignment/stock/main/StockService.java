package com.kakaopay.assignment.stock.main;



import com.kakaopay.assignment.common.DateUtil;
import com.kakaopay.assignment.stock.rate.StockRateService;
import com.kakaopay.assignment.stock.view.StockViewService;
import com.kakaopay.assignment.stock.volume.StockVolumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service("stockService")
public class StockService {

    StockRateService stockRateService;
    StockViewService stockViewService;
    StockVolumeService stockVolumeService;
    StockMapper stockMapper;

    /**
     * 오늘 날짜의 주식 초기 가격을 가져옴
     * @return List<StockVO>
     * */
    public List<StockVO> initStockPriceList(){
        return stockMapper.initStockPriceList(DateUtil.getDate());
    }

    /**
     * 데이터베이스에 저장되어 있는 DB의 데이터를 변경
     * @return String
     * */
    public String dataChange(DataChangeVO dataChangeVO){
        String type = dataChangeVO.getType();
        if(Objects.nonNull(type)){
            type = type.toLowerCase();
            if(type.equals("view")){
                viewDataChange();
            }else if(type.equals("volume")){
                volumeDataChange();
            }else if(type.equals("price")){
                priceDataChange();
            }else {
                type ="all";
                viewDataChange();
                volumeDataChange();
                priceDataChange();
            }
        }else{
            type ="all";
            viewDataChange();
            volumeDataChange();
            priceDataChange();
        }
        return type+" 데이터가 변경되었습니다.";
    }

    /**
     * 데이터베이스에 저장되어 있는 stock_view 테이블의 데이터를 변경
     * */
    public void viewDataChange(){
        stockViewService.viewDataChange();
    }

    /**
     * 데이터베이스에 저장되어 있는 stock_trading_volume 테이블의 데이터를 변경
     * */
    public void volumeDataChange(){
        stockVolumeService.volumeDataChange();
    }

    /**
     * 데이터베이스에 저장되어 있는 stock_price 테이블의 데이터를 변경
     * */
    public void priceDataChange(){
        stockMapper.priceDataChange(DateUtil.getDate());
    }

    /**
     * stock_price 테이블의 마지막 데이터를 현재 시간으로 다시 insert
     * */
    public void toDayDataInsert(){
        stockMapper.toDayDataInsert();
    }

    /**
     * stock_price 테이블에 저장된 데이터들의 날짜를 어제로 변경
     * 테스트를 위해 작성하였으므로, 실제 사용하지 않음
     * */
    public void stockPriceDateChange(){
        stockMapper.stockPriceDateChange();
    }


}
