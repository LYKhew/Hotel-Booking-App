package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RoomConfirmedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_confirmed);

        // Set up UI elements
        Button doneButton = findViewById(R.id.btn_doneroom);

        // Set up the Done button action
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomConfirmedActivity.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
    }
}