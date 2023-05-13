package com.kakaopay.assignment.stock.view;



import com.kakaopay.assignment.common.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockViewMapper {

    public List<StockViewVO> getStockListByView(PagingVO pagingVO);

    public void viewDataChange();
}
