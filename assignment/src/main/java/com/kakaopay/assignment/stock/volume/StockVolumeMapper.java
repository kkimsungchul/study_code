package com.kakaopay.assignment.stock.volume;



import com.kakaopay.assignment.common.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockVolumeMapper {

    public List<StockVolumeVO> getStockListByVolume(PagingVO pagingVO);

    public void volumeDataChange();
}
