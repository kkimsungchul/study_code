package com.sungchul;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.*;

public class CodingTest003 {
    public static void main(String[]args){
        try{
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            int loopCount1 = Integer.parseInt(buffer.readLine());
            String temp1[] = buffer.readLine().split(" ");
            HashSet<String> set = new HashSet<>();
            ArrayList<String> list = new ArrayList<>();
            for(int i=0;i<temp1.length;i++){
                set.add(temp1[i]);
            }

            int loopCount2 = Integer.parseInt(buffer.readLine());
            String temp2[] =  buffer.readLine().split(" ");
            String resultString ="";
            for(int i=0;i<loopCount2;i++){
                if(set.contains(temp2[i])){
                    resultString +="1";
                }else{
                    resultString +="0";
                }

                if(i!=loopCount2-1){
                    resultString +=" ";
                }

            }
            System.out.println(resultString);


            buffer.close();
        }catch(Exception e){


        }

    }
}