package com.kakaopay.assignment.stock.view;


import com.kakaopay.assignment.stock.main.StockVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockViewVO extends StockVO {

    @Schema(name = "view", description  = "조회수", example = "7855")
    private int view;

}
