package com.sungchul;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;

public class DateTest {
    public static void main(String[]args){
//        LocalDateTime now = LocalDateTime.now();
//
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        System.out.println(now.getDayOfWeek().getValue());
//        System.out.println(now.plusDays(5-now.getDayOfWeek().getValue()).getDayOfWeek().getValue());
//        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).format(dateTimeFormatter));
//        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).plusWeeks(0).format(dateTimeFormatter));
//        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).plusWeeks(1).format(dateTimeFormatter));
//        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).plusWeeks(2).format(dateTimeFormatter));
//
//        System.out.println(now.with(TemporalAdjusters.lastDayOfMonth()).plusMonths(1).format(dateTimeFormatter));
//        String nowString = now.plusWeeks(1).format(dateTimeFormatter);
//        System.out.println(nowString);
//        System.out.println(now.getDayOfWeek().getValue());
        ArrayList<HashMap<String,String>> saturdays = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        //다음달 마지막날
        String nextMonthLastDay = now.with(TemporalAdjusters.lastDayOfMonth()).plusMonths(1).format(dateTimeFormatter);
        for(int i=0;;i++){

            if(Integer.parseInt(nextMonthLastDay) < Integer.parseInt(now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).plusWeeks(i).format(dateTimeFormatter))){
                break;
            }else{
                HashMap<String,String> map = new HashMap<>();
                map.put("saturday",now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).plusWeeks(i).format(dateTimeFormatter));
                map.put("sunday",now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).plusWeeks(i).plusDays(1).format(dateTimeFormatter));
                map.put("today",now.format(dateTimeFormatter));
                saturdays.add(map);
            }

        }
        System.out.println(saturdays);

    }
}
