package com.kakaopay.assignment.stock.main;


import com.kakaopay.assignment.stock.up.StockUpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockMapper {

    public List<StockVO> initStockPriceList();

}
