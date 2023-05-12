package com.kakaopay.assignment.common;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPI {


    @Schema(name = "message", description  = "처리 상태", example = "success or fail")
    private String message;

    @Schema(name = "data", description  = "데이터", example = "")
    private Object data;

    @Schema(name = "timestamp", description  = "시간", example = "2023-05-21 17:31:00")
    private LocalDateTime timestamp;




}
