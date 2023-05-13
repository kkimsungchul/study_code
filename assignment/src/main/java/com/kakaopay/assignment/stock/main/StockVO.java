package com.kakaopay.assignment.stock.main;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockVO {
    @Schema(name = "code", description  = "종목 코드", example = "377300")
    private String code;
    @Schema(name = "name", description  = "종목 명", example = "카카오페이")
    private String name;
    @Schema(name = "nowPrice", description  = "현재가", example = "110000")
    private int nowPrice;
    @Schema(name = "initPrice", description  = "오늘 시작가", example = "100000")
    private int initPrice;
    @Schema(name = "strPrice", description  = "현재가(원 단위변환)", example = "110,000")
    private String strPrice;
    @Schema(name = "percent", description  = "등락률", example = "10%")
    private String percent;
    @Schema(name = "color", description  = "등락률에 따른 색상", example = "red")
    private String color;

}
