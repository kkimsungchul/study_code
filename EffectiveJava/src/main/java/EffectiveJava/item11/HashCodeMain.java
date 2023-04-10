package EffectiveJava.item11;

import EffectiveJava.item10.EqualsTestVO;

import java.util.HashMap;

public class HashCodeMain {
    public static void main(String[]args){
        //hashCode 를 정의하기 전에 아래와 같이 key부분에 HashCodeTestVO 로 키를 만들고 값을 성처리로 넣고,
        //get으로 가져오면 null 이 나온다.
        HashMap<HashCodeTestVO,String> map = new HashMap<>();
        map.put(new HashCodeTestVO(1,2,3,"김성철"),"성처리");
        System.out.println(map.get(new HashCodeTestVO(1,2,3,"김성철")));
        
    }
}
