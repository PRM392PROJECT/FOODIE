package com.example.foodie.ui.order;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodie.models.Order;
import com.example.foodie.models.OrderItem;
import com.example.foodie.models.OrderItemRequest;
import com.example.foodie.models.OrderRequest;
import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter {
    private IOrderView view;
    private ApiService apiService;
    private Context context;
    public OrderPresenter(IOrderView view, Context context)  {
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }
    public void order(@NonNull Order order) {
        OrderRequest orderRequest = createOrderRequest(order);
        Call<Order> call = apiService.createOrder(orderRequest);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    view.orderSuccess();
                } else {
                    view.orderFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                view.orderFailed(t.getMessage());
            }
        });
        Log.i("OrderRequest",orderRequest.toString());
    }
    private OrderRequest createOrderRequest(Order order){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setRestaurantId(order.getRestaurantId());
        orderRequest.setUserId(order.getUserId());
        orderRequest.setTotalAmount(order.getTotalAmount());
        orderRequest.setStatus(1);
        List<OrderItemRequest> orderItemRequests = new ArrayList<>();
        for(OrderItem item : order.getOrderItems()){
            OrderItemRequest orderItemRequest = new OrderItemRequest();
            orderItemRequest.setProductId(item.getProduct().getProductId());
            orderItemRequest.setQuantity(item.getQuantity());
            orderItemRequest.setPrice(item.getProduct().getPrice());
            orderItemRequests.add(orderItemRequest);
        }
        orderRequest.setOrderItems(orderItemRequests);
        return  orderRequest;
    }
}
