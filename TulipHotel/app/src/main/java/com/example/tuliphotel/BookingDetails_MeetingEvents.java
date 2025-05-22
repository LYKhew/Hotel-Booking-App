package com.example.tuliphotel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class BookingDetails_MeetingEvents extends AppCompatActivity {

    private String name;
    private int imageResId;

    private Button btnDate, btnTime, btnBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details_meeting_events);

        // Initialize Firebase in your activity (only once per lifecycle)
        FirebaseApp.initializeApp(this); // Ensure this is done elsewhere in the app, not necessarily here

        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            imageResId = intent.getIntExtra("imageResId", 0);
        }

        TextView tvName = findViewById(R.id.tv_room_name);
        ImageView ivImage = findViewById(R.id.iv_room_image);
        TextView tvLocation = findViewById(R.id.tv_location);
        TextView tvDescription = findViewById(R.id.tv_description);
        btnDate = findViewById(R.id.btn_date);
        btnTime = findViewById(R.id.btn_time);
        btnBook = findViewById(R.id.btn_book);

        if (name != null) {
            tvName.setText(name);
        } else {
            tvName.setText("Unknown Room");
        }

        if (imageResId != 0) {
            ivImage.setImageResource(imageResId);
        } else {
            ivImage.setImageResource(R.drawable.img);  // replace with an actual default drawable resource
        }

        // Set the location, description, and image resource based on the room name
        if (name != null) {
            switch (name) {
                case "Meeting Room 1":
                    tvLocation.setText("Block A, Second Floor");
                    tvDescription.setText("The Meeting Room 1 suits medium-sized meetings and workshops, accommodating up to 50 guests. It offers flexible seating arrangements, modern décor, natural light, and stunning city views.");
                    ivImage.setImageResource(R.drawable.meeting_room1);  // replace with actual drawable resource
                    break;
                case "Meeting Room 2":
                    tvLocation.setText("Block B, Third Floor");
                    tvDescription.setText("The Meeting Room 2 is ideal for smaller meetings, accommodating up to 30 guests. It features state-of-the-art AV equipment, comfortable seating, and a professional ambiance.");
                    ivImage.setImageResource(R.drawable.meeting_room_2);  // replace with actual drawable resource
                    break;
                case "Meeting Room 3":
                    tvLocation.setText("Block C, Fourth Floor");
                    tvDescription.setText("Meeting Room 3 is perfect for intimate gatherings and executive meetings. It accommodates up to 20 guests and offers a quiet, professional environment with modern amenities.");
                    ivImage.setImageResource(R.drawable.meeting_room3);  // replace with actual drawable resource
                    break;
                case "Event Room 1":
                    tvLocation.setText("Block D, Ground Floor");
                    tvDescription.setText("Event Room 1 is designed for large events and conferences, with a capacity of up to 200 guests. It features high ceilings, a grand stage, and advanced lighting and sound systems.");
                    ivImage.setImageResource(R.drawable.event_room1);  // replace with actual drawable resource
                    break;
                case "Event Room 2":
                    tvLocation.setText("Block E, First Floor");
                    tvDescription.setText("Event Room 2 is suitable for medium-sized events, accommodating up to 100 guests. It offers flexible setup options, premium AV equipment, and elegant décor.");
                    ivImage.setImageResource(R.drawable.event_room2);  // replace with actual drawable resource
                    break;
                case "Event Room 3":
                    tvLocation.setText("Block F, Second Floor");
                    tvDescription.setText("Event Room 3 is perfect for social gatherings and smaller events, with a capacity of up to 70 guests. It provides a cozy atmosphere, modern facilities, and customizable layout options.");
                    ivImage.setImageResource(R.drawable.event_room3);  // replace with actual drawable resource
                    break;
                default:
                    tvLocation.setText("Unknown Location");
                    tvDescription.setText("No description available for this room.");
                    ivImage.setImageResource(R.drawable.img);  // replace with actual drawable resource
                    break;
            }
        } else {
            tvLocation.setText("Unknown Location");
            tvDescription.setText("No description available for this room.");
            ivImage.setImageResource(R.drawable.img);  // replace with actual drawable resource
        }

        // Date Picker
        btnDate.setOnClickListener(v -> showDatePickerDialog());

        // Time Picker
        btnTime.setOnClickListener(v -> showTimePickerDialog());

        // Handle the book button click here to confirm the booking and navigate to the confirmation activity
        btnBook.setOnClickListener(v -> {
            // Get the selected date and time
            String selectedDate = btnDate.getText().toString();
            String selectedTime = btnTime.getText().toString();

            // Initialize Firebase database reference
            DatabaseReference bookingsRef = FirebaseDatabase.getInstance().getReference("Meeting&Events");

            // Create a new booking object with date, time, room name, etc.
            Booking booking = new Booking(name, selectedDate, selectedTime);

            // Push the booking to Firebase database
            bookingsRef.push().setValue(booking)
                    .addOnSuccessListener(aVoid -> {
                        // Booking successfully saved
                        // Optionally, you can navigate to confirmation activity or show a success message
                        Intent intent1 = new Intent(BookingDetails_MeetingEvents.this, MeetingEvent_roomConfirmedActivity.class);
                        startActivity(intent1);
                    })
                    .addOnFailureListener(e -> {
                        // Handle any errors
                        Toast.makeText(BookingDetails_MeetingEvents.this, "Failed to book room: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> btnDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1),
                year, month, day);

        // Set the minimum date allowed to today's date
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute1) -> btnTime.setText(String.format("%02d:%02d", hourOfDay, minute1)),
                hour, minute, true);
        timePickerDialog.show();
    }
}
