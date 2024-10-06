package com.example.foodie.ui.authen;

import android.util.Log;

import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginPresenter {
    private static String USER_PREFS_NAME = "UserPrefs";
    private ILoginView view;
    private ApiService apiService;
    private Context context; // Add context to access SharedPreferences

    public LoginPresenter(ILoginView view, Context context){
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void login(String email, String password){
        Call<User> call = apiService.login(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful() && response.body() != null){
                    User user = response.body();
                    Log.i("user", user.toString());

                    // Lưu thông tin người dùng sau khi đăng nhập
                    UserInfoManager.saveUserInfo(user, context);
                    view.loginSuccess();
                } else {
                    try {
                        String errorMessage = response.errorBody().string();
                        view.loginFail(errorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                        view.loginFail("An unknown error occurred.");
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.loginFail("Login failed due to network error: " + t.getMessage());
            }
        });
    }


}
