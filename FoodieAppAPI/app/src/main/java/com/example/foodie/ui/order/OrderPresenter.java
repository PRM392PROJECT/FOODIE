package com.example.foodie.ui.order;

import android.content.Context;
import android.util.Log;

import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

public class OrderPresenter {
    private IOrderView view;
    private ApiService apiService;
    private Context context;
    public OrderPresenter(IOrderView view, Context context)  {
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient().create(ApiService.class);
    }
    public void order(int productId, int quantity) {
        User user = UserInfoManager.getUserInfo(context);
        Log.i("user", user.toString());
    }
}
