package com.example.foodie.ui.profile;

import android.content.Context;

import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

public class EditProfilePresent {
    private IEditProfileView view;
    private Context context;
    private ApiService apiService;
    public EditProfilePresent(Context context, IEditProfileView view) {
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }
    public void getProfile() {
        User user = UserInfoManager.getUserInfo(context);
        if (user != null) {
            view.showProfile(user);
        }
    }
}
