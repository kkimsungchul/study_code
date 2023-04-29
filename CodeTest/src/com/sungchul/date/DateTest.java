package com.sungchul.date;

import java.time.LocalDate;

public class DateTest {
    public static void main(String[]args){
        System.out.println(dayEqual("2023-04-30"));
        System.out.println(isDayBeforeNow("2023-04-30"));

    }
    public static boolean dayEqual(String day){
        String[] splitString = day.split("-");
        LocalDate getDate = LocalDate.of(
                Integer.parseInt(splitString[0])
                ,Integer.parseInt(splitString[1])
                ,Integer.parseInt(splitString[2]));
        LocalDate now = LocalDate.now();
        return now.isBefore(getDate);

    }

    /**
     * 현재 날짜가 입력받은 날짜보다 이전인지 구함
     * @param inputDate yyyy-MM-dd 형식으로 입력
     * @return Boolean , true 이전임 , false 지났음
     */
    public static boolean isDayBeforeNow(String inputDate) {
        LocalDate getDate = LocalDate.parse(inputDate);
        LocalDate now = LocalDate.now();
        return getDate.isAfter(now);
    }

}
