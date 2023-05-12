package com.kakaopay.assignment.common;

import com.kakaopay.assignment.stock.main.StockVO;

import java.text.DecimalFormat;
import java.util.List;

public class CommonUtil {


    /**
     * 데이터 가공
     * 주식가격 원단위 변환
     * 주식등략률 계산
     * */
    public static List<?> DatProcessing(List<?> stockList){
        int nowPrice;
        int initPrice;

        for(Object stockVO : stockList){
            nowPrice = ((StockVO)stockVO).getNowPrice();
            initPrice = ((StockVO)stockVO).getInitPrice();
            ((StockVO)stockVO).setStrPrice(changePrice(nowPrice));
            ((StockVO)stockVO).setPercent(makePercent( nowPrice, initPrice ));

        }
        return stockList;
    }

    /**
     * 주식 가격을 원단위로 변환
     * @return stockList
     **/
    public static String changePrice(int nowPrice){
        return DecimalFormat.getInstance().format(nowPrice);
    }


    /**
     * 등락률을 구함
     * 현재값 - 초기값 / 초기값 * 100
     * */
    public static String makePercent(int nowPrice , int initPrice){
        return String.format("%.2f", (nowPrice-initPrice)/(double)initPrice*100)+"%";
    }



}
