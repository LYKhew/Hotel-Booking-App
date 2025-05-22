package com.example.tuliphotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventsRoomAdapter extends RecyclerView.Adapter<EventsRoomAdapter.ViewHolder> {

    private List<EventRoom> mEventsData;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(EventRoom eventRoom);
    }

    public EventsRoomAdapter(List<EventRoom> data, OnItemClickListener listener) {
        this.mEventsData = data;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEventRoomName;
        public ImageView ivEventRoom;
        public Button btnBook;

        public ViewHolder(View view) {
            super(view);
            tvEventRoomName = view.findViewById(R.id.tv_eventRoom_name);
            ivEventRoom = view.findViewById(R.id.iv_eventRoom);
            btnBook = view.findViewById(R.id.btn_eventBook);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventRoom data = mEventsData.get(position);
        holder.tvEventRoomName.setText(data.getName());
        holder.ivEventRoom.setImageResource(data.getImageResId());

        holder.btnBook.setOnClickListener(v -> listener.onItemClick(data));
    }

    @Override
    public int getItemCount() {
        return mEventsData.size();
    }
}
