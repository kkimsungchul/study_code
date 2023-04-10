package EffectiveJava.item10;

import java.util.Objects;

public class EqualsTestVO {

    int firstPhoneNumber;
    int secondPhoneNumber;
    int thirdPhoneNumber;

    String userName;

    public EqualsTestVO(int firstPhoneNumber, int secondPhoneNumber, int thirdPhoneNumber, String userName) {
        this.firstPhoneNumber = firstPhoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.thirdPhoneNumber = thirdPhoneNumber;
        this.userName = userName;
    }

    public int getFirstPhoneNumber() {
        return firstPhoneNumber;
    }

    public void setFirstPhoneNumber(int firstPhoneNumber) {
        this.firstPhoneNumber = firstPhoneNumber;
    }

    public int getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(int secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public int getThirdPhoneNumber() {
        return thirdPhoneNumber;
    }

    public void setThirdPhoneNumber(int thirdPhoneNumber) {
        this.thirdPhoneNumber = thirdPhoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*equals 재정의*/
    @Override
    public boolean equals(Object o){
        //같은객체인지 비교
        if(o ==this){
            return true;
        }
        //타입 비교
        if(!(o instanceof EqualsTestVO)){
            return false;
        }
        //안에 들어있는 필드들의 값 비교
        EqualsTestVO equalsTestVO = (EqualsTestVO)o;
        return equalsTestVO.firstPhoneNumber == firstPhoneNumber && equalsTestVO.secondPhoneNumber==secondPhoneNumber
                && equalsTestVO.thirdPhoneNumber == thirdPhoneNumber && equalsTestVO.userName.equals(userName);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EqualsTestVO that = (EqualsTestVO) o;
//        return firstPhoneNumber == that.firstPhoneNumber && secondPhoneNumber == that.secondPhoneNumber && thirdPhoneNumber == that.thirdPhoneNumber && Objects.equals(userName, that.userName);
//    }
}
