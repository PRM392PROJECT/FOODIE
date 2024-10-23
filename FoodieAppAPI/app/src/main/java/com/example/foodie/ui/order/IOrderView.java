package com.example.foodie.ui.order;

public interface IOrderView {
    void orderSuccess();
    void orderFailed(String message);
    void showLoading();
    void hideLoading();
    void showError(String message);
    void showOrder();
    void orderNow();
}
