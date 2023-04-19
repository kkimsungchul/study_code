package com.sungchul.jpatest.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParsingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;


    private String stockCode;

    private String stockName;

    private int stockCategoryCode;

    private String stockCategoryName;

    private int presentPrice;

    private int yesterdayPrice;

    private int currentPrice;

    private int highPrice;

    private int upperLimitPrice;

    private int lowPrice;

    private int lowerLimitPrice;

    private int tradingVolume;

    private String tradingValue;

    private String direction;

    private int directionCode;

    private int priceGap;

    private int foreignTrade;

    private int institutionTrade;

    private String parsingDate;

    private String parsingDateDetail;

    private int per;

    private int eps;

    private String parsingMemo;

}
