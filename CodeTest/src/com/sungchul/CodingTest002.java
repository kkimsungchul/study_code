package com.sungchul;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class CodingTest002{
    public static void main(String[]args){
        try{
            BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            int count= Integer.parseInt(buffer.readLine());
            int arr[] = new int[count];
            for(int i=0;i<count;i++){
                arr[i] = Integer.parseInt(buffer.readLine());
            }
            Arrays.sort(arr);
            int temp=-1;
            for(int i=0;i<count;i++){
                if(temp!=arr[i]){
                    bufferWriter.write(arr[i]+"\n");
                }
                temp = arr[i];
            }
            buffer.close();
            bufferWriter.flush();
            bufferWriter.close();

        }catch(Exception e){

        }


    }
}