package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RoomBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_booking);

        Button buttonStandard = findViewById(R.id.btn_standardRoom);
        Button buttonDeluxe = findViewById(R.id.btn_deluxeRoom);
        Button buttonSuite = findViewById(R.id.btn_suiteRoom);

        buttonStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start RoomDetailsActivity with "Standard Room" type
                Intent intent = new Intent(RoomBookingActivity.this, RoomDetailsActivity.class);
                intent.putExtra("ROOM_TYPE", "Standard Room");
                startActivity(intent);
            }
        });

        buttonDeluxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start RoomDetailsActivity with "Deluxe Room" type
                Intent intent = new Intent(RoomBookingActivity.this, RoomDetailsActivity.class);
                intent.putExtra("ROOM_TYPE", "Deluxe Room");
                startActivity(intent);
            }
        });

        buttonSuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start RoomDetailsActivity with "Family Room" type
                Intent intent = new Intent(RoomBookingActivity.this, RoomDetailsActivity.class);
                intent.putExtra("ROOM_TYPE", "Suite");
                startActivity(intent);
            }
        });
    }
}