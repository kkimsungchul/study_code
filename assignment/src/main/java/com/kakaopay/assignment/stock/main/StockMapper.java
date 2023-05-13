package com.kakaopay.assignment.stock.main;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockMapper {

    public List<StockVO> initStockPriceList(String date);

    public void toDayDataInsert();

    public void stockPriceDateChange();

    public void priceDataChange(String date);
}
