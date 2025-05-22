package com.example.tuliphotel;


public class Review {

    private String username;
    private float rating;
    private String feedback;

    public Review() {
        // Default constructor required for calls to DataSnapshot.getValue(Review.class)
    }

    public Review(String username, float rating, String feedback) {
        this.username = username;
        this.rating = rating;
        this.feedback = feedback;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

