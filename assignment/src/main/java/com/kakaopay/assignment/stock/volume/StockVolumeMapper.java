package com.kakaopay.assignment.stock.volume;



import com.kakaopay.assignment.stock.view.StockViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockVolumeMapper {

    public List<StockViewVO> test();

}
