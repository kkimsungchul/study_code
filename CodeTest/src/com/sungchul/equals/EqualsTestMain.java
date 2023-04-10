package com.sungchul.equals;

import java.util.HashMap;
import java.util.Objects;

public class EqualsTestMain {
    public static void main(String[]args){
        String name = "김성철";

        System.out.println(Objects.equals(name,"김성철"));

        HashMap<String,String> map1 = new HashMap<>();
        HashMap<String,String> map2 = new HashMap<>();

        System.out.println(map1.equals(map2));
        map1.put("name","김성철");
        System.out.println(map1.equals(map2));
        map2.put("name","김성철");
        System.out.println(map1.equals(map2));
    }
}
