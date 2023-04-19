package com.sungchul.jpatest.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
/*
* 클래스명은 데이터베이스의 테이블명과 똑같게 작성
* ex ) StockList = stock_list
*
*/
public class StockList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name ="stock_code",length = 6)
    private String stockCode;

    @Column(name ="stock_long_code",length = 12)
    private String stockLongCode;

    @Column(name ="stock_name",length = 60)
    private String stockName;

    @Column(name ="stock_category_code",length = 11)
    private int stockCategoryCode;

    @Column(name ="stock_category_name",length = 6)
    private String stockCategoryName;

    @Column(name ="insert_date",length = 14)
    private String insertDate;

    @Column(name ="update_date",length = 14)
    private String updateDate;


}
