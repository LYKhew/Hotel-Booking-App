package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteReview extends AppCompatActivity {

    private EditText etUsername;
    private RatingBar rbStars;
    private EditText etFeedback;
    private Button btnSubmitReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        etUsername = findViewById(R.id.reviewratings_et_username);
        rbStars = findViewById(R.id.rb_stars);
        etFeedback = findViewById(R.id.et_review); // Updated variable initialization
        btnSubmitReview = findViewById(R.id.btn_submit_review);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // No need to update the TextView for feedback
            }
        });

        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                float rating = rbStars.getRating();
                String feedback = etFeedback.getText().toString().trim(); // Read from the EditText

                if (!username.isEmpty() && !feedback.isEmpty()) {
                    saveReviewToFirebase(username, rating, feedback);
                    Toast.makeText(WriteReview.this, "Your review has been submitted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WriteReview.this, ReviewRatingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(WriteReview.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveReviewToFirebase(String username, float rating, String feedback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reviews");

        Review review = new Review(username, rating, feedback);

        // Use username as the key
        myRef.child(username).setValue(review);
    }
}
