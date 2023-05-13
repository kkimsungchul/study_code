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
    public static PagingVO makePaging(String paramPageNum, String paramLimit){
        int maxValue = 100;
        int pageNum  = 0;
        int limit = 20;
        int maxPage =maxValue/limit;

        if(Objects.nonNull(paramPageNum)){
            try{
                pageNum = Integer.valueOf(paramPageNum);
            }catch (NumberFormatException nfe){
                pageNum = 0;
            }
        }
        if(Objects.nonNull(paramLimit)){
            try{
                limit = Integer.valueOf(paramLimit);
                if(limit>100){
                    limit = 100;
                }
            }catch (NumberFormatException nfe){
                limit = 20;
            }
        }

        if(maxValue%limit>0){
            maxPage =maxValue/limit+1;
        }else{
            maxPage =maxValue/limit;
        }


        if(pageNum<=1){
            pageNum=1;
        }else if(pageNum>=maxPage){
            pageNum = maxPage;
        }

        pageNum= (pageNum-1)*limit;

        if(maxValue-pageNum<limit){
            limit = maxValue-pageNum;
        }
        return PagingVO.builder()
                .pageNum(pageNum)
                .limit(limit)
                .toDay(DateUtil.getDate())
                .build();
    }


}
