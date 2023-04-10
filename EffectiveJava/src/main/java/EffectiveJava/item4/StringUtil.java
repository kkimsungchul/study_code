package EffectiveJava.item4;

public class StringUtil{
    public static final String stringValue = "hihi";

    //private 생성자로 객체 생성 방지
    private StringUtil() {
    }

    public static String getString(){
        return stringValue;
    }
}