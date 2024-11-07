package com.example.foodie.ui.authen;

import android.content.Context;
import android.widget.Toast;

import com.example.foodie.models.UserRegister;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private Context context;
    private IRegisterView view;
    private ApiService apiService;

    // Constructor
    public RegisterPresenter(Context context, IRegisterView view) {
        this.context = context;
        this.view = view;
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    // Method to handle registration
    public void register() {
        UserRegister userRegister = view.getUserRegister();
        if (userRegister == null) {
            view.registerFalse("Please fill in all required fields");
            return;
        }
        Call<UserRegister> call = apiService.register(userRegister);
        call.enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.registerSuccess();
                } else {
                    view.registerFalse(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                view.registerFalse("Registration failed: " + t.getMessage());
                Toast.makeText(context, "Registration failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
