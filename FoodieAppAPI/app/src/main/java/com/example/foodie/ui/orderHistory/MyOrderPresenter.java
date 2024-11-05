package com.example.foodie.ui.orderHistory;

import android.content.Context;

import com.example.foodie.models.Order;
import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderPresenter {
    private IOrderListView view;
    private Context context;
    private ApiService apiService;

    public MyOrderPresenter(IOrderListView view, Context context) {
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    public void loadOrderOngoing() {
        User user = UserInfoManager.getUserInfo(context);
        if (user != null) {
            view.showLoading();
            Call<List<Order>> call = apiService.getOrdersByUserId(user.getUserId());
            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        // Lọc các đơn hàng có trạng thái "1" (đang xử lý)
                        List<Order> orders = new ArrayList<>();
                        for (Order order : response.body()) {
                            if (order.getStatus() == 1) {
                                orders.add(order);
                            }
                        }
                        if (!orders.isEmpty()) {
                            view.showOrderOnGoing(orders);
                        } else {
                            view.showOrderEmpty();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    view.showError(t.getMessage());
                    view.showOrderEmpty();

                }
            });
        } else {
            view.showError("User not logged in");
        }

    }

    public void loadOrderHistory() {
        User user = UserInfoManager.getUserInfo(context);
        if (user != null) {
            view.showLoading();
            Call<List<Order>> call = apiService.getOrdersByUserId(user.getUserId());
            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        // Lọc các đơn hàng có trạng thái "1" (đang xử lý)
                        List<Order> orders = new ArrayList<>();
                        for (Order order : response.body()) {
                            if (order.getStatus() ==2) {
                                orders.add(order);
                            }
                        }
                        if (!orders.isEmpty()) {
                            view.showOrderHistory(orders);
                        } else {
                            view.showOrderEmpty();
                        }
                    } else {
                        view.showOrderEmpty();
                    }
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    view.showOrderEmpty();
                    view.showError(t.getMessage());
                }
            });
        } else {
            view.showError("User not logged in");
        }

    }
}
