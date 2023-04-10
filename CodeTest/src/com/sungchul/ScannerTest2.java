package com.sungchul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Scanner;

public class ScannerTest2 {
    public static void main(String[]args){
//        Scanner sc = new Scanner(System.in);

//        int starCount = sc.nextInt();
//        int spaceCount=starCount;
//        String star="*";
//        for(int i=0;i<starCount;i++){
//            for(int j=0;j<spaceCount;j++){
//                System.out.print(" ");
//            }
//            System.out.println(star);
//            star+="*";
//            spaceCount--;
//        }

        ///////////
//        int count=0;
//        int t= sc.nextInt();
//        int target=t;
//        while(true){
//            count++;
//            int a = t/10;
//            int b = t%10;
//            int sum = a+b;
//            t = (b*10)+(sum%10);
//            if(target==t){
//             break;
//            }
//
//        }
//        System.out.println(count);
        ///////////
//        try{
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//            int arraySize = Integer.parseInt(buffer.readLine());
//            String [] arr = buffer.readLine().split(" ");
//            String target = buffer.readLine();
//            int count=0
//            for(int i=0;i<arr.length;i++){
//                if(target.equals(arr[i])){
//                    count++;
//                }
//            }
//            System.out.println(count);
//        }catch (Exception e){
//
//        }
        /////////
//        try{
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//            String [] temp1 = buffer.readLine().split(" ");
//            String [] temp2 = buffer.readLine().split(" ");
//            int target = Integer.parseInt(temp1[1]);
//            String result ="";
//            for(int i=0;i<temp2.length;i++){
//                if(Integer.parseInt(temp2[i])<target){
//
//                    if(result.equals("")){
//                        result +=temp2[i];
//                    }else{
//                        result += " "+temp2[i];
//                    }
//
//                }
//            }
//            buffer.close();
//            System.out.println(result);
//        }catch(Exception e){
//        }
        /////
//        try{
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//            int arr[] = new int[28];
//            boolean []checkArr = new boolean[30];
//            for(int i=0;i<arr.length;i++){
//                arr[i]= Integer.parseInt(buffer.readLine());
//            }
//            for(int i=0;i<arr.length;i++){
//                checkArr[arr[i]-1]=true;
//            }
//
//            for(int i=0;i<checkArr.length;i++){
//                if(checkArr[i]==false){
//                    System.out.println(i+1);
//                }
//            }
//            buffer.close();
//        }catch(Exception e){
//
//        }
        ////////
//        try{
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//            int count = Integer.parseInt(buffer.readLine());
//            String temp[] = buffer.readLine().split(" ");
//            double scoreArr[] = new double[count];
//            double max = Integer.MIN_VALUE;
//            double sum=0;
//            for(int i=0;i<temp.length;i++){
//                scoreArr[i] = Integer.parseInt(temp[i]);
//                if(scoreArr[i]>max){
//                    max=scoreArr[i];
//                }
//            }
//            for(int i=0;i<scoreArr.length;i++){
//                sum+= (scoreArr[i]/max)*100;
//            }
//            System.out.println(sum/count);
//        }catch(Exception e){
//
//        }
        ////

//        try{
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//            int whileCount = Integer.parseInt(buffer.readLine());
//            for(int i=0;i<whileCount;i++){
//                double average=0;
//                double sum=0;
//                String temp[] = buffer.readLine().split(" ");
//                double student[] = new double[temp.length-1];
//                double count=0;
//                for(int j=1;j<=Integer.parseInt(temp[0]);j++){
//                    sum += Integer.parseInt(temp[j]);
//                    student[j-1]=Integer.parseInt(temp[j]);
//                }
//                average = sum/student.length;
//                Arrays.sort(student);
//
//                for(int j=0;j<student.length;j++){
//                    if(student[j]>average){
//                        count++;
//                    }
//                }
//                double result = count/(double)student.length*100;
//                System.out.println(String.format("%.3f",result)+"%");
//            }
//
//
//        }catch(Exception e){
//
//        }


///////
//        try{
//            int [][]arr = new int[9][9];
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//            int max=Integer.MIN_VALUE;
//            int x=0;
//            int y=0;
//            for(int i=0;i<arr[0].length;i++){
//                String temp[] = buffer.readLine().split(" ");
//                for(int j=0;j<arr[0].length;j++){
//                    arr[i][j] = Integer.parseInt(temp[j]);
//                    if(arr[i][j]>max){
//                        max=arr[i][j];
//                        x=i+1;
//                        y=j+1;
//                    }
//                }
//            }
//            System.out.println(max);
//            System.out.println(x + " " + y);
//        }catch (Exception e){
//
//        }
////
//        Scanner sc = new Scanner(System.in);
//        int count = sc.nextInt();
//        String []temp = sc.next().split("");
//        int sum = 0;
//        for(int i=0;i<temp.length;i++){
//            sum += Integer.parseInt(temp[i]);
//        }
//
//        System.out.println(sum);


//        Scanner sc = new Scanner(System.in);
//        int count = sc.nextInt();
//        String result="";
//        for(int k=0;k<count;k++){
//            int loop = sc.nextInt();
//            String word[] = sc.next().split("");
//
//            for(int i=0;i<word.length;i++){
//                for(int j=0;j<loop;j++){
//                    result +=word[i];
//                }
//            }
//            result+="\n";
//        }
//        System.out.println(result);
/////
//        Scanner sc = new Scanner(System.in);
//        int a[] = new int[28];
//        int count=0;
//        int max =Integer.MIN_VALUE;
//        char result=0;
//        String temp = sc.nextLine();
//        temp = temp.toLowerCase();
//        for(int i=97;i<=122;i++){
//            int t= getCharCount(temp,(char)i);
//            a[count]=t;
//            count++;
//            if(t>max){
//                max = t;
//                result = (char)i;
//            }
//        }
//        Arrays.sort(a);
//        if(a[a.length-1]==a[a.length-2]){
//            System.out.println("?");
//        }else{
//            System.out.println(result);
//        }
/////
//        Scanner sc  = new Scanner(System.in);
//        String []a = sc.nextLine().trim().split(" ");
//        System.out.println(a.length);
        /////
        String a="asdf";

        Scanner sc = new Scanner(System.in);
        String []str = sc.nextLine().split("");
        sc.close();
        int result=0;
        for(int i=0;i<str.length;i++){
            char temp = str[i].charAt(0);
            int sum = (int)temp-65;
            if((int)temp>=83){
                sum--;
            }
            if((int)temp==90){
                sum--;
            }
            int time = sum/3+3;
            result +=time;
        }
        System.out.println(result);
        //65 90
    }

    public static int getCharCount(String str , char c){

        return (int)str.chars().filter(s-> s==c).count();
    }

}
