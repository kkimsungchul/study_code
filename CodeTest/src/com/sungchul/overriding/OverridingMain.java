package com.sungchul.overriding;

public class OverridingMain {

    public static void main(String[]args){
        OverridingSuper overridingSuper1 = new OverridingSuper();
        overridingSuper1.test();
        overridingSuper1.bye();


        OverridingSuper overridingSuper2 = new OverridingSub();
        overridingSuper2.test();
        overridingSuper2.hihi();
        overridingSuper2.bye();

        OverridingSub overridingSub1 = new OverridingSub();
        overridingSub1.test();
        overridingSub1.hihi();
        overridingSub1.bye();
        overridingSub1.bye(1);


    }
}
