package com.example.tuliphotel;

public class PlaytopiaBooking {
    public String date;
    public int quantityAdult;
    public int quantityChild;
    public int totalPrice;

    // Default constructor required for calls to DataSnapshot.getValue(PlaytopiaBooking.class)
    public PlaytopiaBooking() {}

    public PlaytopiaBooking(String date, int quantityAdult, int quantityChild, int totalPrice) {
        this.date = date;
        this.quantityAdult = quantityAdult;
        this.quantityChild = quantityChild;
        this.totalPrice = totalPrice;
    }
}
