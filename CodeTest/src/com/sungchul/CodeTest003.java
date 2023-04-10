package com.sungchul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class CodeTest003 {
    private static String temp=null;
//    public static void main(String[]args){
//        Scanner sc = new Scanner(System.in);
//        temp = sc.nextLine();
//        String arr[] = {"c=","c-","dz1","d-","l1","n1","s=","z="};
//        temp = temp.replaceAll("dz=","dz1");
//        temp = temp.replaceAll("nj","n1");
//        temp = temp.replaceAll("lj","l1");
//        int count=0;
//
//        for(int i=0;i<arr.length;i++){
//            count+=replaceCount(arr[i]);
//        }
//        count+=temp.length();
//        System.out.println(count);
//    }
//    public static int replaceCount(String reStr){
//        int defaultLength = temp.length();
//        temp = temp.replaceAll(reStr,"");
//        System.out.println(temp);
//        int replaceLength = temp.length();
//        int minus = defaultLength-replaceLength;
//        return minus/reStr.length();
//    }

//    public static void main(String[]args){
//        try{
//
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//            int loopCount = Integer.parseInt(buffer.readLine());
//            int resultCount=loopCount;
//            for(int i=0;i<loopCount;i++){
//                String line = buffer.readLine();
//                String []arr = line.split("");
//                String tempWord=arr[0];
//                HashSet<String> set = new HashSet<>();
//                set.add(arr[0]);
//
//                for(int j=1;j<arr.length;j++){
//                    if(tempWord.equals(arr[j])){
//
//                    }else{
//                        if(set.contains(arr[j])){
//                            resultCount--;
//                            break;
//                        }else{
//                            set.add(arr[j]);
//                            tempWord=arr[j];
//                        }
//                    }
//                }
//            }
//            System.out.println(resultCount);
//            buffer.close();
//        }catch(Exception e){
//
//        }
//    }


//    public static void main(String[]args){
//        Scanner sc = new Scanner(System.in);
//        long a = sc.nextLong();
//        long b = sc.nextLong();
//        long c = sc.nextLong();
//        long count=0;
//        if(b>c){
//            System.out.println(-1);
//        }else{
//            long temp = c-b;
//            count=a/temp+1;
//            System.out.println(count);
//        }
//    }


//    public static void main(String[]args){
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        int room=1;
//        int sum=6;
//        int count=1;
//        while(true){
//            if(a<=room){
//                break;
//            }else{
//                room+=sum;
//                sum+=6;
//                count++;
//            }
//        }
//        System.out.println(count);
//    }

    //9223372036854775807 9223372036854775808
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        String tempArr[] = sc.nextLine().split(" ");
        String resultString="";
        int tenCheck=0;
        //계산하기 쉽도록 역순으로 정렬
        for(int i=0;i<tempArr.length;i++){
            String a="";
            String t[]= tempArr[i].split("");
            for(int j=t.length-1;j>=0;j--){
                a+=t[j];
            }
            tempArr[i] = a;
        }
        String []tempA;
        String []tempB;
        if(tempArr[0].length()>tempArr[1].length()){
            tempA = tempArr[0].split("");
            tempB = tempArr[1].split("");
        }else{
            tempB = tempArr[0].split("");
            tempA = tempArr[1].split("");
        }


        for(int i=0;i<tempA.length;i++){
            int t = 0;
            if(i>=tempB.length){
                t = Integer.parseInt(tempA[i])+tenCheck;
            }else{
                t = Integer.parseInt(tempA[i])+Integer.parseInt(tempB[i])+tenCheck;
            }

            if(t>=10){
                tenCheck=1;
            }else{
                tenCheck=0;
            }

            int tt = t%10;
            resultString =tt+resultString;
        }

        if (tenCheck==1){
            resultString = 1+resultString;
        }
        System.out.println(resultString);

    }
}
