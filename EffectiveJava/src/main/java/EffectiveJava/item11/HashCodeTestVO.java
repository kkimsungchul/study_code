package EffectiveJava.item11;

import java.util.Objects;

public class HashCodeTestVO {

    int firstPhoneNumber;
    int secondPhoneNumber;
    int thirdPhoneNumber;

    String userName;

    public HashCodeTestVO(int firstPhoneNumber, int secondPhoneNumber, int thirdPhoneNumber, String userName) {
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
//    @Override
//    public boolean equals(Object o){
//        //같은객체인지 비교
//        if(o ==this){
//            return true;
//        }
//        //타입 비교
//        if(!(o instanceof HashCodeTestVO)){
//            return false;
//        }
//        //안에 들어있는 필드들의 값 비교
//        HashCodeTestVO equalsTestVO = (HashCodeTestVO)o;
//        return equalsTestVO.firstPhoneNumber == firstPhoneNumber && equalsTestVO.secondPhoneNumber==secondPhoneNumber
//                && equalsTestVO.thirdPhoneNumber == thirdPhoneNumber && equalsTestVO.userName.equals(userName);
//    }

//    /*hashCode 재정의*/
//    @Override
//    public int hashCode() {
//        int result = Integer.hashCode(firstPhoneNumber);
//        result = 31 * result + Integer.hashCode(secondPhoneNumber);
//        result = 31 * result + Integer.hashCode(thirdPhoneNumber);
//        result = 31 * result + Objects.hashCode(userName);
//
//        return result;
//    }


    /*hashCode 지연 초기화*/
    int hashCode =0;
    @Override
    public int hashCode() {
        int result =hashCode;
        if(result==0){
            result = Integer.hashCode(firstPhoneNumber);
            result = 31 * result + Integer.hashCode(secondPhoneNumber);
            result = 31 * result + Integer.hashCode(thirdPhoneNumber);
            result = 31 * result + Objects.hashCode(userName);
            hashCode = result;
        }
        return result;
    }

    //Intellij 에서 생성한 equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashCodeTestVO that = (HashCodeTestVO) o;
        return firstPhoneNumber == that.firstPhoneNumber && secondPhoneNumber == that.secondPhoneNumber && thirdPhoneNumber == that.thirdPhoneNumber && Objects.equals(userName, that.userName);
    }
    //intelliJ에서 생성한 hashCode
//    @Override
//    public int hashCode() {
//        return Objects.hash(firstPhoneNumber, secondPhoneNumber, thirdPhoneNumber, userName);
//    }

}
