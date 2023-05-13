package com.kakaopay.assignment.stock.rate;


import com.kakaopay.assignment.common.PagingVO;
import com.kakaopay.assignment.stock.main.StockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockRateMapper {

    public List<StockVO> getStockListByRate(PagingVO pagingVO);



}
