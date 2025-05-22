package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class playtopia_booking_confirmation extends AppCompatActivity {
    Button btnDoneBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playtopia_booking_confirmation);

        // Initialize the Done button
        btnDoneBook = findViewById(R.id.btn_done_playtopia);

        // Set click listener for the Done button
        btnDoneBook.setOnClickListener(view -> {
            // Optionally, show a toast to confirm booking completion
            Toast.makeText(playtopia_booking_confirmation.this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(playtopia_booking_confirmation.this,HomepageActivity.class));
            // Finish this activity
            finish();
        });
    }
}