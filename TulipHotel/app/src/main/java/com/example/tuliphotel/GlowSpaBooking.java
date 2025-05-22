package com.example.tuliphotel;

public class GlowSpaBooking {
    public String spaName;
    public String dateTime;
    public double price;

    public GlowSpaBooking() {
        // Default constructor required for calls to DataSnapshot.getValue(Booking.class)
    }

    public GlowSpaBooking(String spaName, String dateTime, double price) {
        this.spaName = spaName;
        this.dateTime = dateTime;
        this.price = price;
    }
}
