package com.kakaopay.assignment.common;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingVO {

    private int limit;
    private int pageNum;
    private String toDay;
    private String order;
}
