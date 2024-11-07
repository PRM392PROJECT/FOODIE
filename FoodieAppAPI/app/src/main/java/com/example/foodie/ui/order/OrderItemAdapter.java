package com.example.foodie.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodie.R;
import com.example.foodie.models.Order;
import com.example.foodie.models.OrderItem;

import java.util.List;

public class OrderItemAdapter extends BaseAdapter {
    private Context context;
    private List<OrderItem> orderItems;
    public  OrderItemAdapter(Context context, List<OrderItem> orderItems){
        this.context = context;
        this.orderItems = orderItems;
    }

    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public OrderItem getItem(int position) {
        return orderItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orderItems.get(position).getOrderItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.order_item_cart,parent,false);
        }
        OrderItem orderItem = orderItems.get(position);
        ImageView imageView = convertView.findViewById(R.id.foodImage);
        String urlImage = orderItem.getProduct().getImages().get(0).getImageUrl();
        TextView priceFood = convertView.findViewById(R.id.foodprice);
        priceFood.setText(orderItem.getProduct().getPrice()+"");
        Glide.with(context)
                .load(urlImage)
                .error(R.drawable.ic_food_load)
                .placeholder(R.drawable.ic_food_load)
                .into(imageView);
        return  convertView;
    }
    public  void updateData(List<OrderItem> orderItems){
        this.orderItems = orderItems;
        notifyDataSetChanged();
    }
}
