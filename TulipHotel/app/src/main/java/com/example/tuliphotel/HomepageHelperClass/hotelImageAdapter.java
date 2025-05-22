package com.example.tuliphotel.HomepageHelperClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tuliphotel.R;
import java.util.List;

public class hotelImageAdapter extends RecyclerView.Adapter<hotelImageAdapter.HotelViewHolder> {

    private List<hotelImageHelper> hotelImages;

    public hotelImageAdapter(List<hotelImageHelper> hotelImages) {
        this.hotelImages = hotelImages;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewhotelimage, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        hotelImageHelper hotelImage = hotelImages.get(position);
        holder.imageView.setImageResource(hotelImage.getImage());
        // Optionally, you can set the title if your `recyclerviewhotelimage` layout includes a TextView for the title.
        // holder.textView.setText(hotelImage.getTitle());
    }

    @Override
    public int getItemCount() {
        return hotelImages.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        // Optionally, if you have a TextView for the title in your layout
        // TextView textView;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hotel_image);
            // Optionally, if you have a TextView for the title in your layout
            // textView = itemView.findViewById(R.id.hotel_title);
        }
    }
}
