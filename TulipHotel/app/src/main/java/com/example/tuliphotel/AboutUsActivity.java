package com.example.tuliphotel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivCall, ivMessages, ivMaps, ivInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ivCall = findViewById(R.id.iv_call);
        ivMessages = findViewById(R.id.iv_message);
        ivMaps = findViewById(R.id.iv_maps);
        ivInstagram = findViewById(R.id.iv_instagram);

        ivCall.setOnClickListener(this);
        ivMessages.setOnClickListener(this);
        ivMaps.setOnClickListener(this);
        ivInstagram.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_call) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + "0143564421"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        } else if (id == R.id.iv_message) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + "0143564421"));  // Only SMS apps respond to this.
            intent.putExtra("sms_body", "Hello, I would like to ask about the hotel reservations.");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        } else if (id == R.id.iv_maps) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri geoLocation = Uri.parse("https://maps.app.goo.gl/Hx5mBZ41ksFs6fPr7?g_st=ac");
            intent.setData(geoLocation);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        } else if (id == R.id.iv_instagram) {
            Uri uri = Uri.parse("https://www.instagram.com/tuliphotel05?igshid=bG16ZngyeGN6NHhs");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            // Set package to Instagram to ensure the intent opens in the Instagram app if available
            intent.setPackage("com.instagram.android");

            // Check if Instagram is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // Instagram not installed, open in browser
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        }
    }
}
