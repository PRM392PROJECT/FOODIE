package com.example.foodie.ui.orderHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodie.R;
import com.example.foodie.models.Order;

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
        if(order.getStatus() == 1){
            status.setText("Completed");
        }
        else {
            status.setText("On Going");
            rate.setVisibility(View.INVISIBLE);
            reOrder.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
    public void updateData(List<Order> orders) {
        this.orders.clear();
        this.orders.addAll(orders);
        notifyDataSetChanged();
    }
}
