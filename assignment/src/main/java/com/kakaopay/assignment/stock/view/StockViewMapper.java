package com.kakaopay.assignment.stock.view;



import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockViewMapper {

    public List<StockViewVO> getStockListByView();

}
