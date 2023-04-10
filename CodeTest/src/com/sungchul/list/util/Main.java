package com.sungchul.list.util;

import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        ListUtil listUtil = new ListUtil();
        List<String> newList = new ArrayList<>();
        List<String> oldList = new ArrayList<>();
        List<Integer> newList2 = new ArrayList<>();
        List<Integer> oldList2 = new ArrayList<>();

        for(int i=0;i<10;i++){
            newList.add(String.valueOf(i));
            newList2.add(i+10);
            if(i%2==0){
                oldList.add(String.valueOf(i));
                oldList2.add(i+10);
            }
        }



        List<String> list1 = listUtil.noneMatchList(newList,oldList);
        for(String a : list1){
            System.out.println(a);
        }

        System.out.println("=======================");
        List<String> list2 = listUtil.matchList(newList,oldList);
        for(String a : list2){
            System.out.println(a);
        }

        List<Integer> list3 = listUtil.noneMatchList(newList2,oldList2);
        for(int a : list3){
            System.out.println(a);
        }

        System.out.println("=======================");
        List<Integer> list4 = listUtil.matchList(newList2,oldList2);
        for(int a : list4){
            System.out.println(a);
        }


    }
}
