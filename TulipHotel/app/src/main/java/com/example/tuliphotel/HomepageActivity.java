package com.example.tuliphotel;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuliphotel.HomepageHelperClass.hotelImageAdapter;
import com.example.tuliphotel.HomepageHelperClass.hotelImageHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class HomepageActivity extends AppCompatActivity {

    ImageView menu, ivReview, ivRoomBooking, ivService, ivMeetingEvents, ivLogout;



    private RecyclerView recyclerView;
    private hotelImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        ivReview = findViewById(R.id.iv_reviewRating);
        ivRoomBooking = findViewById(R.id.iv_roombooking);
        ivService = findViewById(R.id.iv_services);
        ivMeetingEvents = findViewById(R.id.iv_meetingEvents);
        ivLogout=findViewById(R.id.iv_logout);


        ivMeetingEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this, BookMeeting.class));
            }
        });

        ivRoomBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this, RoomBookingActivity.class));
            }
        });

        ivService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this, service.class));
            }
        });

        ivReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this, ReviewRatingActivity.class));
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomepageActivity.this, "Thanks for using our app!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(HomepageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new hotelImageAdapter(getAllHotelImages());
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.navigation_meeting_events) {
                    startActivity(new Intent(HomepageActivity.this, BookMeeting.class));
                    return true;
                } else if (id == R.id.navigation_services) {
                    startActivity(new Intent(HomepageActivity.this, service.class));
                    return true;
                } else if (id == R.id.navigation_room_booking) {
                    startActivity(new Intent(HomepageActivity.this, RoomBookingActivity.class));
                    return true;
                } else if (id == R.id.navigation_review_ratings) {
                    startActivity(new Intent(HomepageActivity.this, ReviewRatingActivity.class));
                    return true;
                } else if(id==R.id.navigation_info){
                    startActivity(new Intent(HomepageActivity.this, AboutUsActivity.class));
                    return true;
                }


                return false;
            }
        });
    }

    private List<hotelImageHelper> getAllHotelImages() {
        List<hotelImageHelper> allHotelImages = new ArrayList<>();
        allHotelImages.add(new hotelImageHelper(null, R.drawable.rv_hotelview, "Hotel View"));
        allHotelImages.add(new hotelImageHelper(null, R.drawable.rv_bedroom1, "Hotel Room"));
        allHotelImages.add(new hotelImageHelper(null, R.drawable.rv_restaurant, "Restaurant"));
        allHotelImages.add(new hotelImageHelper(null, R.drawable.rv_swimmingpool, "Swimming Pool"));
        allHotelImages.add(new hotelImageHelper(null, R.drawable.rv_spahotel, "GlowSpa"));
        allHotelImages.add(new hotelImageHelper(null, R.drawable.rv_playtopia, "Playtopia"));
        return allHotelImages;
    }
}
