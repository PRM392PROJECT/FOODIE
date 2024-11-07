package com.example.foodie.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodie.R;
import com.example.foodie.models.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private List<CartItem> cartItems;
    private Context context;

    public CartAdapter(Context context){
        this.context = context;
        cartItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.order_item_cart,parent,false);
        }
        CartItem cartItem = cartItems.get(position);

        // Find views in the layout
        ImageView foodImage = convertView.findViewById(R.id.foodImage);
        TextView foodName = convertView.findViewById(R.id.foodname);
        TextView foodPrice = convertView.findViewById(R.id.foodprice);
        TextView addDate = convertView.findViewById(R.id.orderDate);

        // Set data into views
        foodName.setText(cartItem.getProductName());
        foodPrice.setText(String.format("$%.2f", cartItem.getPrice()));
        addDate.setText(cartItem.getCreateAt());

        // Use Glide or another image loading library to load the image
        Glide.with(context)
                .load(cartItem.getProductImageUrl())
                .placeholder(R.drawable.ic_food)  // Default image
                .into(foodImage);
        return  convertView;
    }
    public void updateData(List<CartItem> cartItems){
        this.cartItems.clear();
        this.cartItems.addAll(cartItems);
        notifyDataSetChanged();
    }
}
