package com.example.tuliphotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context context;
    private int resource;
    private List<Review> reviewList;

    public ReviewAdapter(Context context, int resource, List<Review> reviewList) {
        super(context, resource, reviewList);
        this.context = context;
        this.resource = resource;
        this.reviewList = reviewList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Review review = reviewList.get(position);

        TextView tvUsername = convertView.findViewById(R.id.tv_username);
        TextView tvRating = convertView.findViewById(R.id.tv_rating);
        TextView tvFeedback = convertView.findViewById(R.id.tv_feedback);

        tvUsername.setText("Username: " + review.getUsername());
        tvRating.setText("Star Rating: " + review.getRating() + "/5.0");
        tvFeedback.setText("Feedback: " + review.getFeedback());

        return convertView;
    }
}
