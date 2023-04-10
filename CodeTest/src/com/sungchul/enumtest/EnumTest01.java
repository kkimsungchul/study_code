package com.sungchul.enumtest;

public enum EnumTest01 {

    RED(1,"red"),BLUE(2,"blue"),ORANGE(3,"green");

    private final int colorInt;
    private final String colorString;

    EnumTest01(int colorInt ,String colorString){
        this.colorInt = colorInt;
        this.colorString = colorString;
    }

    public int getColorInt(){
        return this.colorInt;
    }

    public String getColorString(){
        return this.colorString;
    }
}
