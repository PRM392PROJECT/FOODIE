package com.example.foodie.ui.orderHistory;

import com.example.foodie.models.Order;

import java.util.List;

public interface IOrderListView {
    void showOrderOnGoing(List<Order> orderOnGoings);
    void showOrderHistory(List<Order> orderHistories);
    void showLoading();
    void hideLoading();
    void showError(String message);
    void showOrderEmpty();
}
