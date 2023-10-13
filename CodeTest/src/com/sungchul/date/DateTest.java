package com.sungchul.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {
    public static void main(String[]args){
//        System.out.println(dayEqual("2023-04-30"));
//        System.out.println(isDayBeforeNow("2023-04-30"));

//        String time = "2023-10-13 14:22:32.15333";
//        System.out.println("### time  : " + time);
//        String changeTime = changeDateFormat(time,"yyyy-MM-dd HH:mm:ss.SSSSS","yyyy-MM-dd HH:mm:ss");
//        System.out.println("### Time  : " + changeTime);

        String time = "2023-10-13 14:22:32.271";
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // 수정된 패턴
        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formatDate = null;
        try {
            formatDate = dtFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("### time : " + time);
        System.out.println("###change Time : " + newDtFormat.format(formatDate));

//
//        String time = "2023-10-13 14:22:32.15333";
//        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS");
//        LocalDateTime dateTime = LocalDateTime.parse(time, dtFormatter);
//
//        DateTimeFormatter newDtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println("### time : " + time);
//        System.out.println("### change Time : " + dateTime.format(newDtFormatter));
//
//        String dateString = "2023-10-13 14:22:32";
//        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date parsedDate = dtFormat.parse(dateString);
//            System.out.println("Parsed Date: " + parsedDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        Date currentDate = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = simpleDateFormat.format(currentDate);
//        System.out.println("Formatted Date: " + formattedDate);
    }

    public static String changeDateFormat(String date, String nowFormat, String changeFormat) {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(nowFormat);
        LocalDateTime dateTime = LocalDateTime.parse(date, dtFormatter);

        DateTimeFormatter newDtFormatter = DateTimeFormatter.ofPattern(changeFormat);
        return dateTime.format(newDtFormatter);
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
