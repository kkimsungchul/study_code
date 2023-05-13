package com.kakaopay.assignment.stock.rate;



import com.kakaopay.assignment.common.CommonUtil;
import com.kakaopay.assignment.common.PagingVO;
import com.kakaopay.assignment.stock.main.StockVO;
import com.kakaopay.assignment.stock.view.StockViewVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("StockUpService")
public class StockRateService {


    StockRateMapper stockRateMapper;

    /**
     * stock_price 테이블의 데이터를 조회해옴 (내림차순)
     * @return List<StockVO>
     * */
    @SuppressWarnings("unchecked")
    public List<StockVO> getStockListByRate(String paramPageNum, String order){
        PagingVO pagingVO = CommonUtil.makePaging(paramPageNum);
        pagingVO.setOrder(order);
        List<StockVO> stockRateList = (List<StockVO>) CommonUtil.DatProcessing(stockRateMapper.getStockListByRate(pagingVO));
        return stockRateList;
    }



}
