package com.example.tuliphotel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RoomDetailsActivity extends AppCompatActivity {

    private static final String ARG_ROOM_TYPE = "ROOM_TYPE";
    private int quantity = 1;
    private TextView quantityTextView;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
    private String roomType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        // Retrieve the room type from intent
        Intent intent = getIntent();
        if (intent != null) {
            roomType = intent.getStringExtra(ARG_ROOM_TYPE);
        }

        // Check if roomType is null or empty
        if (roomType == null || roomType.isEmpty()) {
            Toast.makeText(this, "Room type is missing", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set room type text view
        TextView roomTypeTextView = findViewById(R.id.textView);
        roomTypeTextView.setText(roomType);

        // Set image and description based on room type
        ImageView roomImageView = findViewById(R.id.iv_details_room);
        TextView roomDescriptionTextView = findViewById(R.id.tv_description);
        switch (roomType) {
            case "Standard Room":
                roomImageView.setImageResource(R.drawable.room_standard);
                roomDescriptionTextView.setText("The Standard Room at our hotel offers a perfect blend of comfort and functionality, designed to meet the needs of both business and leisure travelers.");
                break;
            case "Deluxe Room":
                roomImageView.setImageResource(R.drawable.room_deluxe);
                roomDescriptionTextView.setText("The Deluxe Room offers additional space and luxury amenities for a more comfortable stay.");
                break;
            case "Suite":
                roomImageView.setImageResource(R.drawable.suite);
                roomDescriptionTextView.setText("The Suite is perfect for families, with ample space and kid-friendly amenities.");
                break;
            default:
                Toast.makeText(this, "Unknown room type", Toast.LENGTH_SHORT).show();
                finish();
                return;
        }

        // Date pickers for check-in and check-out dates
        Button checkInButton = findViewById(R.id.btn_checkin_date);
        Button checkOutButton = findViewById(R.id.btn_checkout_date);

        // Set initial check-in date to current date
        Calendar currentDate = Calendar.getInstance();
        checkInButton.setText(dateFormat.format(currentDate.getTime()));

        checkInButton.setOnClickListener(v -> showDatePickerDialog(checkInButton, currentDate));

        checkOutButton.setOnClickListener(v -> showDatePickerDialog(checkOutButton, currentDate));

        // TextViews for occupancy and add-ons
        TextView occupancyTextView = findViewById(R.id.btn_occupancy);
        occupancyTextView.setText("Single Set"); // Default value, could be changed based on user selection

        occupancyTextView.setOnClickListener(v -> showOccupancyDialog(occupancyTextView));

        TextView addOnTextView = findViewById(R.id.btn_addon);
        addOnTextView.setText("No Add On"); // Default value, could be changed based on user selection

        addOnTextView.setOnClickListener(v -> showAddOnDialog(addOnTextView));

        // Quantity adjustment buttons and TextView
        Button minusQuantityButton = findViewById(R.id.btn_minus_quantity);
        Button addQuantityButton = findViewById(R.id.btn_add_quantity);
        quantityTextView = findViewById(R.id.tv_quantity);

        // Set initial quantity
        quantityTextView.setText(String.valueOf(quantity));

        // Set listeners for quantity adjustment
        minusQuantityButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        addQuantityButton.setOnClickListener(v -> {
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));
        });

        // Proceed to payment button
        Button proceedToPaymentButton = findViewById(R.id.btn_nextpayment);
        proceedToPaymentButton.setOnClickListener(v -> {
            try {
                // Retrieve the selected dates, occupancy, and add-ons
                String checkInDate = checkInButton.getText().toString();
                String checkOutDate = checkOutButton.getText().toString();
                String occupancy = occupancyTextView.getText().toString();
                String addOn = addOnTextView.getText().toString();
                int quantity = Integer.parseInt(quantityTextView.getText().toString());

                // Calculate the price based on room type
                double pricePerNight;
                switch (roomType) {
                    case "Standard Room":
                        pricePerNight = 500;
                        break;
                    case "Deluxe Room":
                        pricePerNight = 850;
                        break;
                    case "Family Room":
                        pricePerNight = 1200;
                        break;
                    default:
                        pricePerNight = 0;
                        break;
                }

                long diffInMillis = dateFormat.parse(checkOutDate).getTime() - dateFormat.parse(checkInDate).getTime();
                long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

                // Ensure there is at least one night booked
                if (diffInDays < 1) {
                    diffInDays = 1;
                }

                double roomCost = pricePerNight * diffInDays * quantity;

                // Add-On cost
                double addOnCost = addOn.equals("No Add On") ? 0 : 50;

                // Total cost before service tax
                double totalPriceBeforeTax = roomCost + addOnCost;

                // Pass data to PaymentActivity
                Intent paymentIntent = new Intent(RoomDetailsActivity.this, PaymentActivity.class);
                paymentIntent.putExtra("roomType", roomType);
                paymentIntent.putExtra("checkInDate", checkInDate);
                paymentIntent.putExtra("checkOutDate", checkOutDate);
                paymentIntent.putExtra("occupancy", occupancy);
                paymentIntent.putExtra("addOn", addOn);
                paymentIntent.putExtra("quantity", quantity);
                paymentIntent.putExtra("price", totalPriceBeforeTax);
                startActivity(paymentIntent);
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(RoomDetailsActivity.this, "Error parsing dates", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog(Button button, Calendar minDate) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RoomDetailsActivity.this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    if (calendar.before(minDate)) {
                        // If selected date is before minDate, show error or do nothing
                        Toast.makeText(RoomDetailsActivity.this, "Selected date is before current date", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    button.setText(dateFormat.format(calendar.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // Set minimum date to current date
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

        datePickerDialog.show();
    }

    private void showOccupancyDialog(TextView occupancyTextView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RoomDetailsActivity.this);
        builder.setTitle("Choose Occupancy");
        String[] options = {"Single Set", "Double Set", "Family Set"};
        builder.setItems(options, (dialog, which) -> occupancyTextView.setText(options[which]));
        builder.show();
    }

    private void showAddOnDialog(TextView addOnTextView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RoomDetailsActivity.this);
        builder.setTitle("Choose Add-On");
        String[] options = {"Breakfast", "Lunch", "Dinner", "No Add On"};
        builder.setItems(options, (dialog, which) -> addOnTextView.setText(options[which]));
        builder.show();
    }
}