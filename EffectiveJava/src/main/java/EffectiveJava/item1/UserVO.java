package EffectiveJava.item1;

public class UserVO {

    private String name;
    private int age;

    public UserVO(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    static class Builder{

        private String name;
        private int age;

        public Builder(){}

        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public UserVO build(){
            return new UserVO(name,age);
        }

    }
}
