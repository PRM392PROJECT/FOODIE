package com.example.foodie.ui.home;

import android.content.Context;

import com.example.foodie.models.Product;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;


public class SearchProductPresenter {
    private Context context;
    private ISearchView view;
    private ApiService apiService ;
    public  SearchProductPresenter(Context context,ISearchView view){
        this.context = context;
        this.view = view;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }
    public void searchProduct(String title){
//        Call<List<Product>> call = apiService
    }
}
