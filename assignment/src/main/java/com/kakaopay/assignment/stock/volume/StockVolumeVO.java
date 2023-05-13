package com.kakaopay.assignment.stock.volume;


import com.kakaopay.assignment.stock.main.StockVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockVolumeVO extends StockVO {

    @Schema(name = "volume", description  = "거래량", example = "7733")
    private int volume;

}
