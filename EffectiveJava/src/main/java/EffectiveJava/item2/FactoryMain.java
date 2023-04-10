package EffectiveJava.item2;

public class FactoryMain {

    public static void main(String[]args){
        Person p1 = Person.createByName("김성철",33);
        Person p2 = Person.createByName("김현식",33);
        System.out.println(p1);
        System.out.println(p2);

        Person p3 = Person.youngPerson("어린이",10);
        Person p4 = Person.oldPerson("노인",80);
        System.out.println(p3.getClass().toString() + " " +p3);
        System.out.println(p4.getClass().toString() + " " +p4);


        Person p5 = Person.createByAge(15);
        Person p6 = Person.createByAge(50);
        System.out.println(p5.getClass().toString() + " " +p5);
        System.out.println(p6.getClass().toString() + " " +p6);

    }
}
