package com.example.tuliphotel;

public class Booking {
    private String roomName;
    private String date;
    private String time;

    public Booking() {
        // Default constructor required for Firebase
    }

    public Booking(String roomName, String date, String time) {
        this.roomName = roomName;
        this.date = date;
        this.time = time;
    }

    // Create getters and setters as needed
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
