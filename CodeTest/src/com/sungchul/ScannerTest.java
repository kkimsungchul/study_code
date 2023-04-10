package com.sungchul;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ScannerTest {
    public static void main(String[]args) throws IOException {
        BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(buffer.readLine());

        for(int i=0;i<count;i++){
            StringTokenizer stringTokenizer = new StringTokenizer(buffer.readLine());
            int sum = Integer.parseInt(stringTokenizer.nextToken()) + Integer.parseInt(stringTokenizer.nextToken());
            bufferWriter.write(sum);
            if(i!=count-1){
                bufferWriter.newLine();
            }

        }
        buffer.close();
        bufferWriter.flush();
        bufferWriter.close();

    }
}
