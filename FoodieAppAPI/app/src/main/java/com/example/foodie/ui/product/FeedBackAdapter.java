package com.example.foodie.ui.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.foodie.R;
import com.example.foodie.models.Feedback;

import java.util.List;

public class FeedBackAdapter extends BaseAdapter {
    private List<Feedback> feedBacks;
    private Context context;
    public FeedBackAdapter(List<Feedback> feedBacks, Context context) {
        this.feedBacks = feedBacks;
        this.context = context;
    }
    @Override
    public int getCount() {
        return feedBacks.size();
    }

    @Override
    public Feedback getItem(int position) {
        return feedBacks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return feedBacks.get(position).getFeedbackId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.feedback_cart, parent, false);
        }
        Feedback feedback = feedBacks.get(position);
        TextView comment = convertView.findViewById(R.id.comment);
        TextView userName = convertView.findViewById(R.id.email);
        RatingBar ratingBar = convertView.findViewById(R.id.rating_bar);
        userName.setText(String.format("%s %s",feedback.getUserFirstName(),feedback.getUserLastName()));
        comment.setText(feedback.getComment());
        ratingBar.setRating(feedback.getRating());
        return  convertView;
    }

    public void updateData(List<Feedback> feedBacks) {
        this.feedBacks.clear();
        this.feedBacks.addAll(feedBacks);
        notifyDataSetChanged();
    }
    public void addData(List<Feedback> feedBacks) {
        this.feedBacks.addAll(feedBacks);
        notifyDataSetChanged();
    }
}

