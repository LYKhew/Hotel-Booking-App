package com.example.tuliphotel;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReviewRatingActivity extends AppCompatActivity {

    private ListView lvReviews;
    private List<Review> reviewList;
    private ReviewAdapter reviewAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_rating);

        lvReviews = findViewById(R.id.listViewReviews);
        Button btnWriteReview = findViewById(R.id.btn_writereview);

        btnWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewRatingActivity.this, WriteReview.class);
                startActivity(intent);
            }
        });

        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this, R.layout.review_item, reviewList);
        lvReviews.setAdapter(reviewAdapter);

        fetchReviewsFromFirebase();
    }

    private void fetchReviewsFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reviews");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reviewList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Review review = snapshot.getValue(Review.class);
                    if (review != null) {
                        reviewList.add(review);
                    }
                }

                reviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(ReviewRatingActivity.this, "Failed to retrieve reviews: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
