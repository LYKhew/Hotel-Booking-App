package com.example.tuliphotel;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class playtopia extends AppCompatActivity {

    TextView tvSelectDate, tvQuantityAdult, tvQuantityChild, tvTotalPrice;
    EditText etSelectDate;
    Button btnAddAdult, btnMinusAdult, btnAddChild, btnMinusChild, btnBookPlaytopia;

    int quantityAdult, quantityChild;
    final int pricePerAdult = 100; // RM
    final int pricePerChild = 85; // RM

    // Firebase Database reference
    DatabaseReference databaseBookings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playtopia);

        // Initialize Firebase Database reference
        databaseBookings = FirebaseDatabase.getInstance().getReference("playtopiaBookings");

        // Initialize UI components
        tvSelectDate = findViewById(R.id.tv_date);
        etSelectDate = findViewById(R.id.et_select_date);
        btnAddAdult = findViewById(R.id.btn_add_adult);
        btnMinusAdult = findViewById(R.id.btn_minus_adult);
        btnAddChild = findViewById(R.id.btn_add_child);
        btnMinusChild = findViewById(R.id.btn_minus_child);
        btnBookPlaytopia = findViewById(R.id.btn_book);
        tvQuantityAdult = findViewById(R.id.tv_quantity_adult);
        tvQuantityChild = findViewById(R.id.tv_quantity_child);
        tvTotalPrice = findViewById(R.id.tv_totalprice_play);

        // Initialize quantities
        quantityAdult = 0;
        quantityChild = 0;

        // Set up click listeners for buttons
        btnAddAdult.setOnClickListener(view -> {
            quantityAdult++;
            tvQuantityAdult.setText(String.valueOf(quantityAdult));
            updateTotalPrice();
        });

        btnMinusAdult.setOnClickListener(view -> {
            if (quantityAdult > 0) {
                quantityAdult--;
                tvQuantityAdult.setText(String.valueOf(quantityAdult));
                updateTotalPrice();
            }
        });

        btnAddChild.setOnClickListener(view -> {
            quantityChild++;
            tvQuantityChild.setText(String.valueOf(quantityChild));
            updateTotalPrice();
        });

        btnMinusChild.setOnClickListener(view -> {
            if (quantityChild > 0) {
                quantityChild--;
                tvQuantityChild.setText(String.valueOf(quantityChild));
                updateTotalPrice();
            }
        });

        btnBookPlaytopia.setOnClickListener(view -> {
            // Create an Intent to start playtopia_booking_confirmation activity
            Intent intent = new Intent(playtopia.this, playtopia_booking_confirmation.class);

            // Collect booking data
            String date = etSelectDate.getText().toString();
            int totalPrice = calculateTotalPrice();

            // Create a new PlaytopiaBooking object
            PlaytopiaBooking booking = new PlaytopiaBooking(date, quantityAdult, quantityChild, totalPrice);

            // Save to Firebase Database
            String bookingId = databaseBookings.push().getKey();
            databaseBookings.child(bookingId).setValue(booking);

            // Pass data to the intent
            intent.putExtra("Date", date);
            intent.putExtra("QuantityAdult", quantityAdult);
            intent.putExtra("QuantityChild", quantityChild);
            intent.putExtra("TotalPrice", totalPrice);

            // Start the activity
            startActivity(intent);
        });

        // Date picker setup
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etSelectDate.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(playtopia.this, (view1, selectedYear, selectedMonth, dayOfMonth) -> {
                selectedMonth = selectedMonth + 1;
                String date = dayOfMonth + "/" + selectedMonth + "/" + selectedYear;
                etSelectDate.setText(date);
            }, year, month, day);
            dialog.show();
        });
    }

    // Method to update the total price
    private void updateTotalPrice() {
        int totalPrice = calculateTotalPrice();
        tvTotalPrice.setText("Total Price: RM " + totalPrice);
    }

    // Calculate total price based on quantities and prices
    private int calculateTotalPrice() {
        return (quantityAdult * pricePerAdult) + (quantityChild * pricePerChild);
    }
}
