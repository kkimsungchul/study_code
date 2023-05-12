package com.kakaopay.assignment.stock.up;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stockUpService")
public class StockUpService {

    @Autowired
    StockUpMapper stockUpMapper;

    public List<StockUpVO> test(){
        return stockUpMapper.test();
    }
}
