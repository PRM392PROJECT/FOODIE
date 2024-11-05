package com.example.foodie.ui.home;

import android.content.Context;

import com.example.foodie.models.Category;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private ApiService apiService ;
    private IHomeView view;
    private Context context;
    public HomePresenter(IHomeView view,Context context){
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }
    public void getFoodCategory() {
        Call<List<Category>> call = apiService.getFoodCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categoryFoods = response.body();
                    categoryFoods.sort(Comparator.comparingInt(Category::getCategoryId));
                    view.loadTablayout(categoryFoods);
                } else {
                    // Handle error
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Handle failure
            }
        });
    }


}
