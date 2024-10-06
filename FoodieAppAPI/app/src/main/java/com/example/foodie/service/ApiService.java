package com.example.foodie.service;

import com.example.foodie.models.Category;
import com.example.foodie.models.Feedback;
import com.example.foodie.models.Product;
import com.example.foodie.models.User;
import com.example.foodie.models.ViewPage;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("categories")
    Call<List<Category>> getFoodCategories();

    @GET("products/get-page")
    Call<ViewPage<Product>> getFoods(
            @Query("categoryId") int categoryId,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );

    @GET("products/get-byId/{productId}")
    Call<Product> getFoodById(@Path("productId") int foodId);

    @GET("productfeedbacks/get-page")
    Call<ViewPage<Feedback>> getFeedbacks(
            @Query("productId") int productId,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );
    @POST("users/login")
    Call<User> login(
            @Query("email") String email,
            @Query("password") String password
    );
}
