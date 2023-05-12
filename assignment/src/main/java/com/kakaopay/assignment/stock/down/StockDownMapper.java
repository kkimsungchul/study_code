package com.kakaopay.assignment.stock.down;


import com.kakaopay.assignment.stock.up.StockUpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockDownMapper {

    public List<StockUpVO> test();

}
