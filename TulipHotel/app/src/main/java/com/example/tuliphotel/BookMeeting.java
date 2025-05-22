package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class BookMeeting extends AppCompatActivity {

    private RecyclerView recyclerMeeting;
    private RecyclerView recyclerEvents;
    private MeetingRoomAdapter meetingAdapter;
    private EventsRoomAdapter eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_meeting);

        // Find RecyclerViews by ID
        recyclerMeeting = findViewById(R.id.recycler_meeting);
        recyclerEvents = findViewById(R.id.recycler_events);

        // Use horizontal layout managers
        recyclerMeeting.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerEvents.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Sample data
        List<String> meetingRooms = Arrays.asList("Meeting Room 1", "Meeting Room 2", "Meeting Room 3");
        List<Integer> meetingImages = Arrays.asList(R.drawable.meeting_room1, R.drawable.meeting_room_2, R.drawable.meeting_room3);
        List<EventRoom> eventRooms = Arrays.asList(
                new EventRoom("Event Room 1", R.drawable.event_room1),
                new EventRoom("Event Room 2", R.drawable.event_room2),
                new EventRoom("Event Room 3", R.drawable.event_room3)
        );

        // Specify adapters
        meetingAdapter = new MeetingRoomAdapter(meetingRooms, meetingImages, this::showMeetingBookingDetails);
        eventsAdapter = new EventsRoomAdapter(eventRooms, this::showEventBookingDetails);
        recyclerMeeting.setAdapter(meetingAdapter);
        recyclerEvents.setAdapter(eventsAdapter);
    }

    private void showMeetingBookingDetails(String name, int imageResId) {
        Intent intent = new Intent(this, BookingDetails_MeetingEvents.class);
        intent.putExtra("name", name);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }

    private void showEventBookingDetails(EventRoom eventRoom) {
        Log.d("BookMeeting", "Event Room clicked: " + eventRoom.getName()); // Log click event
        Intent intent = new Intent(this, BookingDetails_MeetingEvents.class);
        intent.putExtra("name", eventRoom.getName());
        intent.putExtra("imageResId", eventRoom.getImageResId());
        startActivity(intent);
    }
}
