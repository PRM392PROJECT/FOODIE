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
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    private User getUserInfo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS_NAME, Context.MODE_PRIVATE);
        User user = new User();
        user.setUserId(sharedPreferences.getInt("userId", -1));  // -1 là giá trị mặc định nếu không tìm thấy dữ liệu
        user.setFirstName(sharedPreferences.getString("firstName", null));
        user.setLastName(sharedPreferences.getString("lastName", null));
        user.setEmail(sharedPreferences.getString("userEmail", null));
        user.setPhoneNumber(sharedPreferences.getString("phoneNumber", null));
        user.setAddress(sharedPreferences.getString("address", null));
        user.setAvatarUrl(sharedPreferences.getString("avatarUrl", null));
        user.setRoleId(sharedPreferences.getInt("roleId", -1));
        user.setRoleName(sharedPreferences.getString("roleName", null));
        return user;
    }
    public void loadProfile() {
        User user = getUserInfo();
        if (user != null) {
            view.loadProfile(user);
        }
    }
    public void logOut(){
        UserInfoManager.clearUserInfo(context);
        loadProfile();
    }
}
