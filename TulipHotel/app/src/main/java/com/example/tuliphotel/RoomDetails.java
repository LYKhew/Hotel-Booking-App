package com.example.tuliphotel;

public class RoomDetails {
    private String roomType;
    private String checkInDate;
    private String checkOutDate;
    private String occupancy;
    private String addOn;
    private int quantity;

    // Constructor
    public RoomDetails() {
    }

    public RoomDetails(String roomType, String checkInDate, String checkOutDate, String occupancy, String addOn, int quantity) {
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.occupancy = occupancy;
        this.addOn = addOn;
        this.quantity = quantity;
    }

    // Getters and setters
    // ...
}

