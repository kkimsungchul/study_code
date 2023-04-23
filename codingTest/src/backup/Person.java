package backup;

public class Person {


    private String name;
    private int age;
    private String address;
    private String sex;
    private boolean marry;

    private Person(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.sex = builder.sex;
        this.marry = builder.marry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isMarry() {
        return marry;
    }

    public void setMarry(boolean marry) {
        this.marry = marry;
    }

    @Override
    public String toString() {
        return "backup.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", marry=" + marry +
                '}';
    }

    public static class Builder{
        private String name;
        private int age;
        private String address;
        private String sex;
        private boolean marry;

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setAddress(String address){
            this.address = address;
            return this;
        }

        public Builder setSex(String sex){
            this.sex = sex;
            return this;
        }

        public Builder setMarry(boolean marry){
            this.marry = marry;
            return this;
        }


        public Person build(){
            return new Person(this);
        }


    }
}
