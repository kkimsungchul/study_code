package com.kakaopay.assignment.stock.view;



import com.kakaopay.assignment.common.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service("stockViewService")
public class StockViewService {


    StockViewMapper stockViewMapper;

    @SuppressWarnings("unchecked")
    public List<StockViewVO> getStockListByView(){
        List<StockViewVO> list = (List<StockViewVO>) CommonUtil.DatProcessing(stockViewMapper.getStockListByView());

        return list;
    }
}
