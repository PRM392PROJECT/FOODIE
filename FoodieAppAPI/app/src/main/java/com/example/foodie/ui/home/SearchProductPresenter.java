package com.example.foodie.ui.home;

import android.content.Context;
import android.util.Log;

import com.example.foodie.models.Product;
import com.example.foodie.models.ViewPage;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchProductPresenter {
    private Context context;
    private ISearchView view;
    private ApiService apiService ;
    public  SearchProductPresenter(Context context,ISearchView view){
        this.context = context;
        this.view = view;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    public void searchFoodByName(String name){
        view.showLoading(); // Hiển thị loading khi bắt đầu gọi API
        Call<ViewPage<Product>> call = apiService.searchProductByName(name);
        call.enqueue(new Callback<ViewPage<Product>>() {
            @Override
            public void onResponse(Call<ViewPage<Product>> call, Response<ViewPage<Product>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    ViewPage<Product> responseData = response.body();
                    List<Product> foods = responseData.getItems();
                    if (!foods.isEmpty()) {
                        view.showProducts(foods);
                    } else{
                        view.showEmpty();
                    }
                }
            }

            @Override
            public void onFailure(Call<ViewPage<Product>> call, Throwable t) {
                view.hideLoading(); // Ẩn loading khi có lỗi xảy ra
            }
        });
    }

}
