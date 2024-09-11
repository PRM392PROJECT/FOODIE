package com.example.foodie.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodie.R;
import com.example.foodie.models.Food;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private List<Food> foodList;
    private LayoutInflater inflater;
    private int layoutId;

    // Constructor với danh sách thực phẩm và layout
    public FoodAdapter(Context context, List<Food> foodList, int layoutId) {
        this.foodList = foodList;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
            holder = new ViewHolder();
            holder.foodImage = convertView.findViewById(R.id.foodImage);
            holder.foodName = convertView.findViewById(R.id.foodName);
            holder.foodPrice = convertView.findViewById(R.id.foodPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Gán dữ liệu cho item
        //Food food = foodList.get(position);
        //holder.foodName.setText(food.getName());
        //holder.foodPrice.setText(food.getPrice());
        //holder.foodImage.setImageResource(food.getImageResource());

        return convertView;
    }

    private static class ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
    }
}
