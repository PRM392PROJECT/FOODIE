package com.example.foodie.ui.cart;

import android.content.Context;
import android.util.Log;

import com.example.foodie.models.Cart;
import com.example.foodie.models.User;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {
    private ICartView view;
    private Context context;
    private ApiService apiService;
    public CartPresenter(Context context,ICartView view){
        this.context = context;
        this.view = view;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    public void getCart() {
        User user = UserInfoManager.getUserInfo(context);
        if (user != null) {
            Call<Cart> call = apiService.getCart(user.getUserId());
            call.enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Cart cart = response.body();
                        view.showCart(cart);
                        Log.i("Cart", cart.toString());
                    } else {
                        Log.e("Cart", "Failed to retrieve cart: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    Log.e("Cart", "API call failed: " + t.getMessage());
                }
            });
        } else {
            Log.e("Cart", "User is null, cannot retrieve cart.");
        }
    }

}
