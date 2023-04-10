package EffectiveJava.item4;

import java.util.HashMap;

public class InstanceMain {
    public static void main(String[]args){
        //아래와 생성자를 지정하지 않을 경우 같이 정적 클래스이지만 생성이 됨
        //private 생성자를 적용하면 인스턴스 생성이 불가능함
        //StringUtil stringUtil = new StringUtil();


        HashMap<String,Object> map = new HashMap<>();

        map.put("dept",null);

        String dept = (String)map.get("dept");
        System.out.println(dept);
        
    }
}
