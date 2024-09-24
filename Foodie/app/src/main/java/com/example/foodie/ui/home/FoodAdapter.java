package com.example.foodie.ui.home;

import android.content.Context;
import android.util.Log;
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

    // Constructor với danh sách thực phẩm và context
    public FoodAdapter(Context context, List<Food> foodList) {
        this.foodList = foodList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Food getItem(int position) {
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
            convertView = inflater.inflate(R.layout.fragment_food_item, parent, false);
            holder = new ViewHolder();
            holder.foodImage = convertView.findViewById(R.id.foodImage);
            holder.foodName = convertView.findViewById(R.id.foodName);
            holder.foodPrice = convertView.findViewById(R.id.foodPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

       try{
           Food food = getItem(position);
           holder.foodName.setText(food.getName());
           holder.foodPrice.setText(String.format("%.2f$", food.getPrice()));
       }catch (Exception ex){
           Log.e("FoodAdapter", "Error getting food item: " + ex.getMessage());
       }
        // Load image using a library like Glide or Picasso
        // Example: Glide.with(holder.foodImage.getContext()).load(food.getImageUrl()).into(holder.foodImage);

        return convertView;
    }

    public boolean isDataEmpty() {
        return foodList.isEmpty();
    }

    public void setData(List<Food> foods) {
        this.foodList = foods;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
    }
}
