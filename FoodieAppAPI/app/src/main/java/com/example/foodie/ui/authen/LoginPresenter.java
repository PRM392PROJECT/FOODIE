package com.example.foodie.ui.authen;

import android.util.Log;

import com.example.foodie.models.Login;
import com.example.foodie.models.Token;
import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.JwtUtils;
import com.example.foodie.untils.UserInfoManager;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

public class LoginPresenter {
    private static String USER_PREFS_NAME = "UserPrefs";
    private ILoginView view;
    private ApiService apiService;
    private Context context; // Add context to access SharedPreferences

    public LoginPresenter(ILoginView view, Context context){
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }
    // login no token
    public void login(String email, String password){
        Login login = new Login(email,password);
        Call<User> call = apiService.login(login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful() && response.body() != null){
                    User user = response.body();
                    Log.i("user", user.toString());
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
    // login with token
    public void login2(String email, String password) {
        Login login = new Login(email, password);
        Call<Token> call = apiService.login2(login);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Token token = response.body();
                    User user = decodeTokenGetUser(token.getTokenString());
                    UserInfoManager.saveUserInfo(user, context);
                    UserInfoManager.saveToken(token,context);
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
            public void onFailure(Call<Token> call, Throwable t) {
                view.loginFail("Login failed due to network error: " + t.getMessage());
            }
        });
    }

    public User decodeTokenGetUser(String tokenString) {
        try {
            String payload = JwtUtils.decodeJwt(tokenString);
            Log.i("JWT Payload", payload);

            JSONObject jsonObject = new JSONObject(payload);
            if (!jsonObject.has("User")) {
                Log.e("JWT Decode Error", "User field not found in payload.");
                return null;
            }

            // get string JSON from "User"
            String userString = jsonObject.getString("User");
            Log.i("User String", userString); // Log user string
            // replace string JSON with object JSON
            JSONObject userObject = new JSONObject(userString);
            User user = new User();
            user.setUserId(userObject.getInt("UserId"));
            user.setFirstName(userObject.getString("FirstName"));
            user.setLastName(userObject.getString("LastName"));
            user.setPhoneNumber(userObject.getString("PhoneNumber"));
            user.setEmail(userObject.getString("Email"));
            user.setAddress(userObject.getString("Address"));
            user.setCreateAt(userObject.has("CreateAt") ? userObject.getString("CreateAt") : null);
            user.setUpDateAt(userObject.has("UpDateAt") ? userObject.getString("UpDateAt") : null);
            user.setAvatarUrl(userObject.has("AvatarUrl") ? userObject.getString("AvatarUrl") : null);
            user.setRoleId(userObject.getInt("RoleId"));
            user.setRoleName(userObject.getString("RoleName"));
            return user;
        } catch (Exception e) {
            Log.e("JWT Decode Error", "Failed to decode JWT: " + e.getMessage());
        }
        return null;
    }

}
