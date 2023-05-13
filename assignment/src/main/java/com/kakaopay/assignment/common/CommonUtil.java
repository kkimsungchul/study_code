package com.kakaopay.assignment.common;

import com.kakaopay.assignment.stock.main.StockVO;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public class CommonUtil {


    /**
     * 데이터 가공
     * 주식가격 원단위 변환
     * 주식등략률 계산
     * 주식 표현 색상 생성
     * */
    public static List<?> DatProcessing(List<?> stockList){
        int nowPrice;
        int initPrice;

        for(Object stockVO : stockList){
            nowPrice = ((StockVO)stockVO).getNowPrice();
            initPrice = ((StockVO)stockVO).getInitPrice();
            ((StockVO)stockVO).setStrPrice(changePrice(nowPrice));
            ((StockVO)stockVO).setColor(makeColor( nowPrice, initPrice));

        }
        return stockList;
    }

    /**
     * 주식 가격을 원단위로 변환
     * @return stockList
     **/
    public static String changePrice(int nowPrice){
        return DecimalFormat.getInstance().format(nowPrice)+"원";
        
        
    }


    /**
     * 등락률을 구함
     * 현재값 - 초기값 / 초기값 * 100
     * @return percent
     * */
    public static String makePercent(int nowPrice , int initPrice){
        return String.format("%.2f", (nowPrice-initPrice)/(double)initPrice*100)+"%";
    }

    /**
     * 종목 등락률 색상을 정함
     * @return percent
     * */
    public static String makeColor(int nowPrice , int initPrice){
        double price = (nowPrice-initPrice)/(double)initPrice*100;
        String color = "gray";
        if(price < 0){
            color="blue";
        }else if(price > 0){
            color="red";
        }


        return color;
    }

    /**
     * 페이징 처리
     * @return PagingVO
     **/
    public static PagingVO makePaging(String paramPageNum){
        int pageNum=0;

        if(Objects.isNull(paramPageNum)){
            paramPageNum="0";
        }
        try{
            pageNum = Integer.valueOf(paramPageNum);
        }catch (NumberFormatException nfe){
            pageNum = 0;
        }

        //페이지가 1보다 작거나 같을 경우
        if(pageNum<=1){
            pageNum=1;
        //페이지가 5보다 클경우
        }else if(pageNum>5){
            pageNum = 5;
        }

        pageNum= (pageNum-1)*20;
        return PagingVO.builder()
                .pageNum(pageNum)
                .limit(20)
                .toDay(DateUtil.getDate())
                .build();
    }


}
