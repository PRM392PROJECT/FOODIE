package com.example.foodie.ui.product;

import android.content.Context;

import com.example.foodie.models.Feedback;
import com.example.foodie.models.Product;
import com.example.foodie.models.ViewPage;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailPresenter {
    private ApiService apiService;
    private IProductDetailView view;
    private int foodId;
    private Context context;

    public ProductDetailPresenter(IProductDetailView view,Context context) {
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
        this.view = view;
    }

    public void loadFoodDetail(int foodId) {
        this.foodId = foodId;
        Call<Product> call = apiService.getFoodById(foodId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                view.hideLoadingFeedback();
                if (response.isSuccessful() && response.body() != null) {
                    Product food = response.body();
                    view.loadFoodDetail(food);
                } else {
                    view.showError("Unable to load food details. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                view.showError("An error occurred: " + t.getMessage());
            }
        });
    }
    public void loadFeedbacks(int productId,int pageNumber,int pageSize) {
        Call<ViewPage<Feedback>> call = apiService.getFeedbacks(productId, pageNumber, pageSize);
        call.enqueue(new Callback<ViewPage<Feedback>>() {
            @Override
            public void onResponse(Call<ViewPage<Feedback>> call, Response<ViewPage<Feedback>> response) {
               if(response.isSuccessful() && response.body() != null){
                   ViewPage<Feedback> viewPage = response.body();
                   List<Feedback> feedbacks = viewPage.getItems();
                   view.loadFeedbacks(feedbacks);
               }
            }
            @Override
            public void onFailure(Call<ViewPage<Feedback>> call, Throwable t) {

            }
        });

    }
}
