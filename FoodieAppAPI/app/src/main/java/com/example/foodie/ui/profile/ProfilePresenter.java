package com.example.foodie.ui.profile;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

public class ProfilePresenter {
    private static String USER_PREFS_NAME = "UserPrefs";
    private IProfileView view;
    private Context context;
    private ApiService apiService;
    public ProfilePresenter(IProfileView view, Context context){
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    private User getUserInfo() {
        User user = UserInfoManager.getUserInfo(context);
        return user;
    }
    public void loadProfile() {
        User user = getUserInfo();
        view.loadProfile(user);
    }
    public void logOut(){
        UserInfoManager.clearUserInfo(context);
        loadProfile();
    }
}
