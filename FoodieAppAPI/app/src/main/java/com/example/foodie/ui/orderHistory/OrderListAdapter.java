package com.example.foodie.ui.orderHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.Guideline;

import com.bumptech.glide.Glide;
import com.example.foodie.R;
import com.example.foodie.models.Order;

import org.w3c.dom.Text;

import java.util.List;

public class OrderListAdapter extends BaseAdapter {

    private List<Order> orders;
    private Context context;

    public OrderListAdapter(Context context, List<Order> orders) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Order getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orders.get(position).getOrderId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.order_detail_cart,parent,false);
        }
        Order order = getItem(position);
        TextView status = convertView.findViewById(R.id.orderStatus);
        Button rate = convertView.findViewById(R.id.btnRate);
        Button reOrder = convertView.findViewById(R.id.btnReOrder);
        TextView orderDate = convertView.findViewById(R.id.orderDate);
        orderDate.setText(order.getOrderDate());
        TextView productName = convertView.findViewById(R.id.foodname);
        ImageView image = convertView.findViewById((R.id.foodImage));
        status.setText(order.getStatusMessage());
        if(!order.getOrderItems().isEmpty()){
            productName.setText(order.getOrderItems().get(0).getProduct().getName());
        }
        TextView total = convertView.findViewById(R.id.foodprice);
        total.setText(String.format("%sđ",order.getTotalAmount()));
        if(order.getOrderItems() !=null && !order.getOrderItems().isEmpty()){
            String urlImage = order.getOrderItems().get(0).getProduct().getImages().get(0).getImageUrl();
            Glide.with(convertView).load(urlImage).into(image);
        }
        if(order.getStatus() == 1){

            rate.setVisibility(View.GONE);
            reOrder.setVisibility(View.GONE);
        }
        return convertView;
    }
    public void updateData(List<Order> orders) {
        this.orders.clear();
        this.orders.addAll(orders);
        notifyDataSetChanged();
    }
}
