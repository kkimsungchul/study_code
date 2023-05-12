package com.kakaopay.assignment.stock.main;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockVO {
    private String code;
    private String name;
    private int nowPrice;
    private int initPrice;
    private String strPrice;
    private String percent;

}
