package com.sungchul;

import java.text.SimpleDateFormat;
import java.util.*;

public class CodeTest002 {

    static String answer= "";
    public static void main(String []args) throws Exception{

//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//        System.out.println(max);
//        System.out.println(min);
            Scanner scan = new Scanner(System.in);
            int count = scan.nextInt();
            int whileCount = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            while(whileCount<count){
                int temp = scan.nextInt();
                System.out.println("## temp : " + temp);
                if(temp>max){
                    max = temp;
                }
                if(temp<min){
                    min = temp;
                }
                whileCount++;
            }

            System.out.println(min*max);



        //String p = "()))((()";
        //bracket(p);

        //HashMap<String,String> map = new HashMap<>();
        //System.out.println(map.get("Strasdf"));
        //priceCal();


    }

    public static long priceCal() throws Exception{
        String a = "05:34";
        String b = "07:59";
        SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);
        Date format1 = f.parse(a);
        Date format2 = f.parse(b);


        System.out.println(format1.getTime());
        System.out.println(format2.getTime());
        long diffMin = (format2.getTime() - format1.getTime()) / 60000; //분 차이

        return diffMin;
    }

    public static void bracket(String p){
        String first="";
        String second="";

        int count=0;
        int index=0;
        //문자열 분리
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='('){
                count++;
            }else if(p.charAt(i)==')'){
                count--;
            }
            if(count==0 && p.length()>0){
                String tempString="";
                first = p.substring(index,i+1);
                tempString = first.substring(1,first.length()-1);
                tempString  = sortString(tempString);
                answer += "(" + tempString + ")";
                index=i+1;
                System.out.println("index      : " + index);
                System.out.println("first      : " + first);
                System.out.println();
            }
        }
        System.out.println("### p      : " + p);
        System.out.println("### answer : " + answer);

    }

    public static String sortString(String first){
        String resultString="";

        for(int i=0;i<first.length();i++){
            if(first.charAt(i)=='('){
                resultString = "("+resultString;
            }else if(first.charAt(i)==')'){
                resultString = resultString+")";
            }
        }
        return resultString;
    }





}
