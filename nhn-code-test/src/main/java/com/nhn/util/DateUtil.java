package com.nhn.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtil {
    /*yyyyMMdd 로 현재 날짜 리턴*/
    public static String getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return now.format(dateTimeFormatter);
    }

    /*yyyy 로 현재 연도 리턴*/
    public static String getYear() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        return now.format(dateTimeFormatter);
    }
    /*HHmmss 로 현재 시간 리턴*/
    public static String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        return now.format(dateTimeFormatter);
    }
    /*지정한 형식으로 출력*/
    public static String getTime(String strformat){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(strformat);
        return now.format(dateTimeFormatter);
    }
    /*yyyyMM 로 현재 날짜 리턴*/
    public static String getMonth(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        return now.format(dateTimeFormatter);
    }


}
