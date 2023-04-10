package com.sungchul;

class ParkingVo{
    private String carNumber;
    private String in;
    private String out;
    private String parkingFee;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(String parkingFee) {
        this.parkingFee = parkingFee;
    }

    @Override
    public String toString() {
        return "ParkingVo{" +
                "carNumber='" + carNumber + '\'' +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                ", parkingFee='" + parkingFee + '\'' +
                '}';
    }
}
