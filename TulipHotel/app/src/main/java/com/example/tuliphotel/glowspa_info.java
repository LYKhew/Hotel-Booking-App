package com.example.tuliphotel;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class glowspa_info extends AppCompatActivity {

    TextView tvGlowspaName, tvPrice,tvDescription;
    EditText etDate, etTime;
    Button btnBook;
    ImageView imgGlowspa;

    // Firebase Database reference
    DatabaseReference databaseBookings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glowspa_info);

        // Initialize Firebase Database reference
        databaseBookings = FirebaseDatabase.getInstance().getReference("bookings");

        // Find UI elements by ID
        tvGlowspaName = findViewById(R.id.tv_name_glowspa);
        tvPrice = findViewById(R.id.tv_price);
        etDate = findViewById(R.id.et_date_spa);
        etTime = findViewById(R.id.et_time_spa);
        btnBook = findViewById(R.id.btn_spa);
        imgGlowspa = findViewById(R.id.img_glowspa);
        tvDescription = findViewById(R.id.tv_description_spa);

        // Retrieve data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String spaName = intent.getStringExtra("SpaName");
            double price = intent.getDoubleExtra("Price", 0.0);
            int imageResId = intent.getIntExtra("ImageResId", R.drawable.default_image);
            String description = intent.getStringExtra("Description");

            // Set data to UI elements
            tvGlowspaName.setText(spaName);
            tvPrice.setText("RM" + String.format("%.2f", price));
            imgGlowspa.setImageResource(imageResId);
            tvDescription.setText(description);
        }

        // Date picker dialog
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(glowspa_info.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                etDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Time picker dialog
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(glowspa_info.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                etTime.setText(hourOfDay + ":" + String.format("%02d", minute));
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });


        // Book button click listener
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract data from UI
                String selectedDate = etDate.getText().toString();
                String selectedTime = etTime.getText().toString();
                String spaName = tvGlowspaName.getText().toString();
                double price = Double.parseDouble(tvPrice.getText().toString().replace("RM", ""));

                // Create a new Booking object
                GlowSpaBooking booking = new GlowSpaBooking(spaName, selectedDate + " " + selectedTime, price);

                // Save to Firebase Database
                String bookingId = databaseBookings.push().getKey();
                databaseBookings.child(bookingId).setValue(booking);

                // Create an Intent to start the bookingConfirmationDetail activity
                Intent intent = new Intent(glowspa_info.this, bookingConfirmationDetail.class);
                intent.putExtra("SpaName", spaName);
                intent.putExtra("Date", selectedDate + " " + selectedTime);
                intent.putExtra("Price", price);
                startActivity(intent);

                // Show a toast message to confirm booking
                Toast.makeText(glowspa_info.this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
