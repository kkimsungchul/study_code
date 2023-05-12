package com.kakaopay.assignment.stock.up;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockUpMapper {

    public List<StockUpVO> test();

}
