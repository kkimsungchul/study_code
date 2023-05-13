package com.kakaopay.assignment.stock.main;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataChangeVO {
    @Schema(name = "type", description  = "갱신할 데이터 타입", example = "all or view or volume or price")
    private String type;
}
