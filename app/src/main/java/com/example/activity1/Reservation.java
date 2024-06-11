package com.example.activity1;

public class Reservation {
    private int reservationId;
    private String carName;
    private int driverLicense;
    private String startDate;
    private String endDate;
    private int totalFee;

    public Reservation(int reservationId, String carName, int driverLicense, String startDate, String endDate, int totalFee) {
        this.reservationId = reservationId;
        this.carName = carName;
        this.driverLicense = driverLicense;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalFee = totalFee;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        return "ReservationId: " + reservationId + ", CarName: " + carName + ", DriverLicense: " + driverLicense
                + ", StartDate: " + startDate + ", EndDate: " + endDate + ", TotalFee: " + totalFee;
    }
}
