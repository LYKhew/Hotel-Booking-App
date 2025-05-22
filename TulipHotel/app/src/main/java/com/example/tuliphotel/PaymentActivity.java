package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    private String roomType;
    private String checkInDate;
    private String checkOutDate;
    private String occupancy;
    private String addOn;
    private int quantity;
    private double price;
    private double serviceTax;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Retrieve room details from Intent
        Intent intent = getIntent();
        if (intent != null) {
            roomType = intent.getStringExtra("roomType");
            checkInDate = intent.getStringExtra("checkInDate");
            checkOutDate = intent.getStringExtra("checkOutDate");
            occupancy = intent.getStringExtra("occupancy");
            addOn = intent.getStringExtra("addOn");
            quantity = intent.getIntExtra("quantity", 1);
            price = intent.getDoubleExtra("price", 0.0);
        }

        // Calculate service tax and total price
        serviceTax = price * 0.06; // Assuming 6% service tax
        totalPrice = price + serviceTax;

        // Display the room details
        TextView roomTypeTextView = findViewById(R.id.tv_typeRoom);
        roomTypeTextView.setText(roomType);
        TextView checkInDateTextView = findViewById(R.id.tv_checkInDate);
        checkInDateTextView.setText(checkInDate);
        TextView checkOutDateTextView = findViewById(R.id.tv_checkOutDate);
        checkOutDateTextView.setText(checkOutDate);
        TextView occupancyTextView = findViewById(R.id.tv_occupancy);
        occupancyTextView.setText(occupancy);
        TextView addOnTextView = findViewById(R.id.tv_addOn);
        addOnTextView.setText(addOn);
        TextView quantityTextView = findViewById(R.id.tv_quantitypay);
        quantityTextView.setText(String.valueOf(quantity));
        TextView priceTextView = findViewById(R.id.tv_priceRoom);
        priceTextView.setText(String.format("RM%.2f", price));
        TextView serviceTaxTextView = findViewById(R.id.tv_serviceTax);
        serviceTaxTextView.setText(String.format("RM%.2f", serviceTax));
        TextView totalPriceTextView = findViewById(R.id.tv_totalPriceRoom);
        totalPriceTextView.setText(String.format("RM%.2f", totalPrice));

        // Set up the Pay button
        Button payButton = findViewById(R.id.button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store the room details in the database
                storeRoomDetailsInDatabase();

                // Transition to the RoomConfirmedActivity
                Intent intent = new Intent(PaymentActivity.this, RoomConfirmedActivity.class);
                startActivity(intent);
            }
        });
    }

    private void storeRoomDetailsInDatabase() {
        // Get a reference to the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Roombooking");

        // Create a unique key for the new booking
        String bookingId = ref.push().getKey();

        // Create a map with the room details
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("roomType", roomType);
        bookingDetails.put("checkInDate", checkInDate);
        bookingDetails.put("checkOutDate", checkOutDate);
        bookingDetails.put("occupancy", occupancy);
        bookingDetails.put("addOn", addOn);
        bookingDetails.put("quantity", quantity);
        bookingDetails.put("price", price);
        bookingDetails.put("serviceTax", serviceTax);
        bookingDetails.put("totalPrice", totalPrice);

        // Store the booking details in the database
        if (bookingId != null) {
            ref.child(bookingId).setValue(bookingDetails);
        }
    }
}
