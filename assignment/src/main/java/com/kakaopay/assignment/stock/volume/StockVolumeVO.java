package com.kakaopay.assignment.stock.volume;


import com.kakaopay.assignment.stock.main.StockVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockVolumeVO extends StockVO {

    private int volume;

}
