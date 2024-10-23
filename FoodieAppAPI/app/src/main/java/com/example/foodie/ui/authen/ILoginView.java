package com.example.foodie.ui.authen;

public interface ILoginView {
    void showLoading();
    void hideLoading();
    void showError(String message);
    void loginSuccess();
    void loginFail(String message);
}
