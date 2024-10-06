package com.example.foodie.ui.orderHistory;

import android.content.Context;

import com.example.foodie.models.Order;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;

import java.util.ArrayList;
import java.util.List;

public class MyOrderPresenter {
    private IOrderListView view;
    private Context context;
    private ApiService apiService;
    public MyOrderPresenter( IOrderListView view, Context context)
    {
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void loadOrderOngoing(){
        List<Order> orders = new ArrayList<>();
        for(int i=0;i<10;i++){
            Order order = new Order();
            order.setStatus(0);
            orders.add(order);
        }
        if(orders.isEmpty()){
            view.showOrderEmpty();
            return;
        }
        view.showOrderOnGoing(orders);
    }
    public  void loadOrderHistory(){
        List<Order> orders = new ArrayList<>();
        for(int i=0;i<10;i++){
            Order order = new Order();
            order.setStatus(1);
            orders.add(order);
        }
        if(orders.isEmpty()){
            view.showOrderEmpty();
            return;
        }
        view.showOrderHistory(orders);
    }
}
