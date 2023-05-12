package com.kakaopay.assignment.stock.down;



import com.kakaopay.assignment.stock.up.StockUpMapper;
import com.kakaopay.assignment.stock.up.StockUpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stockDownService")
public class StockDownService {

    @Autowired
    StockDownMapper stockDownMapper;

    public List<StockUpVO> test(){
        return stockDownMapper.test();
    }
}
