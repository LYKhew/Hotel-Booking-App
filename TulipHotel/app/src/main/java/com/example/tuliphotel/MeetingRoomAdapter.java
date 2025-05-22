package com.example.tuliphotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MeetingRoomAdapter extends RecyclerView.Adapter<MeetingRoomAdapter.ViewHolder> {

    private List<String> meetingRooms;
    private List<Integer> meetingImages;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String name, int imageResId);
    }

    public MeetingRoomAdapter(List<String> meetingRooms, List<Integer> meetingImages, OnItemClickListener listener) {
        this.meetingRooms = meetingRooms;
        this.meetingImages = meetingImages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String roomName = meetingRooms.get(position);
        int imageResId = meetingImages.get(position);
        holder.tvRoomName.setText(roomName);
        holder.ivRoomImage.setImageResource(imageResId);

        holder.btnDetails.setOnClickListener(v -> listener.onItemClick(roomName, imageResId));
    }

    @Override
    public int getItemCount() {
        return meetingRooms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomName;
        ImageView ivRoomImage;
        Button btnDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.tv_meetingroom_name); // Correct ID
            ivRoomImage = itemView.findViewById(R.id.iv_meetingRoom); // Correct ID
            btnDetails = itemView.findViewById(R.id.btn_meetingBook); // Correct ID
        }
    }
}
