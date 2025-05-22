package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MeetingEvent_roomConfirmedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_event_room_confirmed);

        // Set up UI elements
        Button doneButton = findViewById(R.id.btn_doneroom);

        // Set up the Done button action
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetingEvent_roomConfirmedActivity.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
    }
}
