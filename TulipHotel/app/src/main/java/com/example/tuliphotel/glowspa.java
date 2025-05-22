package com.example.tuliphotel;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class glowspa extends AppCompatActivity implements View.OnClickListener {
    Button btnBookNormalFacial;
    Button btnBookFullBody;
    Button btnBookUltimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glowspa); // Set the layout for the glowspa activity

        // Find the buttons by their IDs
        btnBookNormalFacial = findViewById(R.id.btn_book_normal_facial);
        btnBookFullBody = findViewById(R.id.btn_book_fullbody);
        btnBookUltimate = findViewById(R.id.btn_book_ultimate_foot);

        // Set click listeners for the buttons
        btnBookNormalFacial.setOnClickListener(this);
        btnBookFullBody.setOnClickListener(this);
        btnBookUltimate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(glowspa.this, glowspa_info.class); // Create an Intent to start the glowspa_info activity

        if (view.getId() == R.id.btn_book_normal_facial) {
            intent.putExtra("SpaName", "Normal Facial (60 minutes)");
            intent.putExtra("Price", 120.00); // Set the price for Normal Facial
            intent.putExtra("ImageResId", R.drawable.normalfacial); // Set the image resource ID for Normal Facial
            intent.putExtra("Description", "The Normal Facial at Glow Spa is a rejuvenating treatment designed to refresh and revitalize your skin. This 60-minute session focuses on deep cleansing, exfoliation, and hydration to enhance your natural glow. It is ideal for maintaining a clear, youthful complexion.");
        } else if (view.getId() == R.id.btn_book_fullbody) {
            intent.putExtra("SpaName", "Full Body Massage (60 minutes)");
            intent.putExtra("Price", 100.00); // Set the price for Full Body Massage
            intent.putExtra("ImageResId", R.drawable.fullbody); // Set the image resource ID for Full Body Massage
            intent.putExtra("Description", "Experience the ultimate in relaxation with the Full Body Massage at Glow Spa. This 60-minute session is designed to ease muscle tension and promote deep relaxation, covering all major muscle groups.");
        } else if (view.getId() == R.id.btn_book_ultimate_foot) {
            intent.putExtra("SpaName", "Ultimate Foot Massage (45 minutes)");
            intent.putExtra("Price", 60.00); // Set the price for Ultimate Foot Massage
            intent.putExtra("ImageResId", R.drawable.ultimatefoot); // Set the image resource ID for Ultimate Foot Massage
            intent.putExtra("Description", "The Ultimate Foot Massage at Glow Spa offers a luxurious escape for your feet. This 45-minute session provides relief and relaxation through gentle pressure and reflexology techniques, leaving your feet rejuvenated and pampered.");
        }
        startActivity(intent); // Start the glowspa_info activity
    }
}
