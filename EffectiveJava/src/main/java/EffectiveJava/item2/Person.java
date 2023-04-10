package EffectiveJava.item2;

public class Person{
    private String name;
    private int age;
    private String address;

    public Person(String name , int age , String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public static Person youngPerson(String name,int age){
        return new YoungPerson(name,age);
    }

    public static Person oldPerson(String name,int age){
        return new OldPerson(name,age);
    }


    static Person createByName(String name,int age){
        return new Person(name,age,null);
    }

    static Person createByAddress(String address , int age){
        return new Person(null,age,address);
    }

    static Person createByAge(int age){

        if(age<20){
            return youngPerson(null,age);
        }else{
            return oldPerson(null,age);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}

class YoungPerson extends Person{

    public YoungPerson(String name,int age){
        super(name,age,null);
    }


}

class OldPerson extends Person{

    public OldPerson(String name,int age) {
        super(name,age,null);
    }
}