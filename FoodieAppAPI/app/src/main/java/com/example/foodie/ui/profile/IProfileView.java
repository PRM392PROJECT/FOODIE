package com.example.foodie.ui.profile;

import com.example.foodie.models.User;

public interface IProfileView {
    void checkLogin();
    void loadProfile(User user);
}
