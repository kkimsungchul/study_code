package com.sungchul.overriding;

public class OverridingSub extends OverridingSuper{

    @Override
    public void test(){
        System.out.println("하위 클래스에서 호출");
    }


    public void bye(int a){
        System.out.println("안녕히가세요");
    }
}
