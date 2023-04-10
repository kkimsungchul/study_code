package com.sungchul.enumtest;


//기존에 사용하던 방식
//class Fruit{
//    public static final Fruit APPLE = new Fruit();
//    public static final Fruit PEACH = new Fruit();
//    public static final Fruit BANANA = new Fruit();
//}

//class Company{
//    public static final Company GOOGLE = new Company();
//    public static final Company APPLE = new Company();
//    public static final Company ORACLE = new Company();
//}


enum Fruit{
    //상수를 만들면서 생성자를 넣어줌
    APPLE("red") ,
    PEACH("pink") ,
    BANANA("yellow");

    //생성자는 상수 갯수만큼 호출됨
    Fruit(String color){
        System.out.println("Call Constructor : " + this);
        this.color = color;
    }

    private String color;

    public String getColor(){
        return this.color;
    }




}

enum Company{
    GOOGLE, APPLE , ORACLE;
}

public class EnumTestMain{

    //상수
    private final static int ORANGE = 11;
    private final static int BLACK = 12;

    private final static int GOOGLE = 1;
    private final static int APPLE = 2;
    private final static int ORACLE = 3;

    public static void main(String[]args){

        for(Fruit f : Fruit.values()){
            System.out.println(f.getColor());
        }


        Fruit type = Fruit.BANANA;
        switch (type){
            case APPLE:
                System.out.println("사과 " + " 색상 : " + Fruit.APPLE.getColor());
                break;
            case PEACH:
                System.out.println("복숭아" + " 색상 : " + Fruit.PEACH.getColor());
                break;
            case BANANA:
                System.out.println("바나나" + " 색상 : " + Fruit.BANANA.getColor());
                break;
        }




//        System.out.println(EnumTest01.RED.getColorInt());
//        System.out.println(EnumTest01.RED.getColorString());
//        System.out.println(EnumTest01.BLUE.getColorInt());
//        System.out.println(EnumTest01.BLUE.getColorString());
    }

}
