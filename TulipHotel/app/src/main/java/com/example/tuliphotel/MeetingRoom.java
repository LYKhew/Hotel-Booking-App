package com.example.tuliphotel;

public class MeetingRoom {
    private String name;
    private int imageResId;

    public MeetingRoom(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
