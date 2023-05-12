package com.kakaopay.assignment.stock.main;


import com.kakaopay.assignment.stock.down.StockDownService;
import com.kakaopay.assignment.stock.up.StockUpService;
import com.kakaopay.assignment.stock.view.StockViewService;
import com.kakaopay.assignment.stock.volume.StockVolumeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("stockService")
public class StockService {

    StockUpService stockUpService;
    StockDownService stockDownService;
    StockViewService stockViewService;
    StockVolumeService stockVolumeService;
    StockMapper stockMapper;

    public List<StockVO> initStockPriceList(){
        return stockMapper.initStockPriceList();
    }


}
