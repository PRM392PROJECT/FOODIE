package com.example.foodie.ui.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodie.R;
import com.example.foodie.models.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> foods;

    public ProductAdapter(Context context, List<Product> foods) {
        this.context = context;
        this.foods = foods;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_cart, parent, false);
        }

        Product food = foods.get(position);
        TextView textViewName = convertView.findViewById(R.id.food_name);
        TextView textViewPrice = convertView.findViewById(R.id.food_price);
        // Có thể thêm ImageView nếu bạn có ảnh thực phẩm

        textViewName.setText(food.getName());
        textViewPrice.setText(String.valueOf(food.getPrice()));

        return convertView;
    }

    public void updateData(List<Product> newFoods) {
        foods.clear();
        foods.addAll(newFoods);
        notifyDataSetChanged();
    }
}
