package com.example.tuliphotel;

public class EventRoom {
    private String name;
    private int imageResId;

    public EventRoom(String name, int imageResId) {
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
