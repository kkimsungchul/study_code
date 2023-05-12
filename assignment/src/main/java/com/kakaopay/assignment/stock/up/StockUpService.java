package com.kakaopay.assignment.stock.up;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("StockUpService")
public class StockUpService {


    StockUpMapper stockUpMapper;

    public List<StockUpVO> test(){
        return stockUpMapper.test();
    }
}
