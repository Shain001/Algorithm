package com.oo.undergroundSystem;

public class Passenger {
    private int id;
    private int checkInTime;
    private int checkOutTime;
    private Station checkinStation;
    private Station checkoutStation;

    public Passenger(int id) {
        this.id = id;
    }

    public void checkIn(Station checkinStation, int checkInTime) {
        this.checkInTime = checkInTime;
        this.checkinStation = checkinStation;
    }

    public void checkout(Station checkoutStation, int checkOutTime) {
        this.checkOutTime = checkOutTime;
        this.checkoutStation = checkoutStation;

        checkoutStation.addCheckout(this.checkinStation, checkOutTime-checkInTime);
    }


}
