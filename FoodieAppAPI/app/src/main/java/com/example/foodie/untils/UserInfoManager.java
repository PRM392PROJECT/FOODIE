package com.example.foodie.untils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodie.models.User;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoManager {

    private static final String USER_PREFS_NAME = "UserPrefs";

    public static User getUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS_NAME, Context.MODE_PRIVATE);
        User user = new User();
        // Lấy dữ liệu từ SharedPreferences
        user.setUserId(sharedPreferences.getInt("userId", -1));  // -1 là giá trị mặc định nếu không tìm thấy dữ liệu
        user.setFirstName(sharedPreferences.getString("firstName", null));
        user.setLastName(sharedPreferences.getString("lastName", null));
        user.setEmail(sharedPreferences.getString("userEmail", null));
        user.setPhoneNumber(sharedPreferences.getString("phoneNumber", null));
        user.setAddress(sharedPreferences.getString("address", null));
        user.setAvatarUrl(sharedPreferences.getString("avatarUrl", null));
        user.setRoleId(sharedPreferences.getInt("roleId", -1));
        user.setRoleName(sharedPreferences.getString("roleName", null));
        if(user.getUserId() ==-1){
            return null;
        }
        return user;
    }
    public static void saveUserInfo(User user,Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Lưu các thông tin của người dùng
        editor.putInt("userId", user.getUserId());
        editor.putString("firstName", user.getFirstName());
        editor.putString("lastName", user.getLastName());
        editor.putString("userEmail", user.getEmail());
        editor.putString("phoneNumber", user.getPhoneNumber());
        editor.putString("address", user.getAddress());
        editor.putString("avatarUrl", user.getAvatarUrl());
        editor.putInt("roleId", user.getRoleId());
        editor.putString("roleName", user.getRoleName());

        editor.apply(); // Lưu thay đổi vào SharedPreferences
    }
    public static void clearUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

