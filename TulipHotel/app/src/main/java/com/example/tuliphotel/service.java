package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class service extends AppCompatActivity {

    Button buttonBookSpa;
    Button buttonBookPlaytopia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // Find UI views by their IDs
        buttonBookSpa = findViewById(R.id.btn_book_spa);
        buttonBookPlaytopia = findViewById(R.id.btn_book_playtopia);

        // Set click listeners for buttons
        buttonBookSpa.setOnClickListener(view -> {
            Toast.makeText(service.this, "Service : Glow Spa", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(service.this, glowspa.class));
        });

        buttonBookPlaytopia.setOnClickListener(view -> {
            Toast.makeText(service.this, "Service : Playtopia", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(service.this, playtopia.class));
        });
    }
}