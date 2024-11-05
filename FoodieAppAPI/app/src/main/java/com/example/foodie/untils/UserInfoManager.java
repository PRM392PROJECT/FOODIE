package com.example.foodie.untils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodie.models.Token;
import com.example.foodie.models.User;

public class UserInfoManager {

    private static final String USER_PREFS_NAME = "UserPrefs";
    private static final String TOKEN_PREFS_NAME = "TokenPrefs";
    public static User getUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS_NAME, Context.MODE_PRIVATE);
        User user = new User();

        user.setUserId(sharedPreferences.getInt("userId", -1));
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

        editor.putInt("userId", user.getUserId());
        editor.putString("firstName", user.getFirstName());
        editor.putString("lastName", user.getLastName());
        editor.putString("userEmail", user.getEmail());
        editor.putString("phoneNumber", user.getPhoneNumber());
        editor.putString("address", user.getAddress());
        editor.putString("avatarUrl", user.getAvatarUrl());
        editor.putInt("roleId", user.getRoleId());
        editor.putString("roleName", user.getRoleName());

        editor.apply();
    }
    public static void clearUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public static Token getToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_PREFS_NAME, Context.MODE_PRIVATE);
        Token token = new Token();
        token.setTokenString(sharedPreferences.getString("tokenString",null));
        token.setExpiration(sharedPreferences.getString("expiration",null));
        return token;
    }
    public static void saveToken(Token token,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tokenString",token.getTokenString());
        editor.putString("expiration",token.getExpiration());
        editor.apply();
    }
}

