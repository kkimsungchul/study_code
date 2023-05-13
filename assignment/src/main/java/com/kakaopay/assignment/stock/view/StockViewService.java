package com.kakaopay.assignment.stock.view;


import com.kakaopay.assignment.common.CommonUtil;
import com.kakaopay.assignment.common.PagingVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("stockViewService")
public class StockViewService {


    StockViewMapper stockViewMapper;


    /**
     * stock_view 테이블의 데이터를 조회해옴
     * @return List<StockViewVO>
     * */
    @SuppressWarnings("unchecked")
    public List<StockViewVO> getStockListByView(String paramPageNum, String limit){


        PagingVO pagingVO = CommonUtil.makePaging(paramPageNum,limit);
        List<StockViewVO> stockViewList = (List<StockViewVO>) CommonUtil.DatProcessing(stockViewMapper.getStockListByView(pagingVO));

        return stockViewList;
    }

    /**
     * 데이터베이스에 저장되어 있는 stock_view 테이블의 데이터를 변경
     * */
    public void viewDataChange(){
        stockViewMapper.viewDataChange();
    }

}
