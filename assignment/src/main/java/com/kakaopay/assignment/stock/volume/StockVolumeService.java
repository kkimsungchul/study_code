package com.kakaopay.assignment.stock.volume;

import com.kakaopay.assignment.stock.view.StockViewVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("stockVolumeService")
public class StockVolumeService {


    StockVolumeMapper stockVolumeMapper;

    public List<StockViewVO> test(){
        return stockVolumeMapper.test();
    }
}
