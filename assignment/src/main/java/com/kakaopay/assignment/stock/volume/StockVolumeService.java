package com.kakaopay.assignment.stock.volume;

import com.kakaopay.assignment.common.CommonUtil;
import com.kakaopay.assignment.common.PagingVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("stockVolumeService")
public class StockVolumeService {


    StockVolumeMapper stockVolumeMapper;

    /**
     * stock_trading_volume 테이블의 데이터를 조회해옴
     * @return List<StockViewVO>
     * */
    @SuppressWarnings("unchecked")
    public List<StockVolumeVO> getStockListByVolume(String paramPageNum, String limit){


        PagingVO pagingVO = CommonUtil.makePaging(paramPageNum,limit);
        List<StockVolumeVO> stockVolumeList = (List<StockVolumeVO>) CommonUtil.DatProcessing(stockVolumeMapper.getStockListByVolume(pagingVO));

        return stockVolumeList;
    }

    /**
     * 데이터베이스에 저장되어 있는 stock_trading_volume 테이블의 데이터를 변경
     * */
    public void volumeDataChange(){
        stockVolumeMapper.volumeDataChange();
    }
}
