package com.sungchul;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    public static void main(String[]args){
        LocalDate now = LocalDate.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        String nowString = now.plusMonths(1).format(dateTimeFormatter);
        System.out.println(nowString);
    }
}
