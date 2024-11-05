package com.example.foodie.ui.authen;

import com.example.foodie.models.UserRegister;

public interface IRegisterView {
    void registerFalse(String mess);
    void registerSuccess();
    UserRegister getUserRegister();
}
