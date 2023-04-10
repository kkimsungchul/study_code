package com.sungchul.enummmm;

public class EnumMain {
    public static void main(String[]args){
        Singleton singleton = Singleton.INSTANCE;
        //PrivateTest aaa = new PrivateTest();
        PrivateTest.getString();
        StringUtil.getString();
        singleton.say();
    }
}
