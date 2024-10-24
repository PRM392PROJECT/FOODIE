package com.example.foodie.service;

import android.media.session.MediaSession;

import com.example.foodie.models.Category;
import com.example.foodie.models.Feedback;
import com.example.foodie.models.Login;
import com.example.foodie.models.Order;
import com.example.foodie.models.OrderRequest;
import com.example.foodie.models.Product;
import com.example.foodie.models.Token;
import com.example.foodie.models.User;
import com.example.foodie.models.UserUpdateRequest;
import com.example.foodie.models.ViewPage;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("products/search-by-name/{name}")
    Call<ViewPage<Product>> searchProductByName(@Path("name") String name);

    @GET("productfeedbacks/get-page")
    Call<ViewPage<Feedback>> getFeedbacks(
            @Query("productId") int productId,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );
    @POST("users/login")
    Call<User> login(@Body Login login);
    @PUT("users/update")
    Call<User> updateUser( @Body UserUpdateRequest userUpdateRequest);

    @GET("orders/get-order-by-userId/{userId}")
    Call<List<Order>> getOrdersByUserId(
            @Path("userId") int userId
    );
    @POST("orders/create-order")
    Call<Order> createOrder(@Body OrderRequest orderRequest);
    @POST("users/authen/login")
    Call<Token> login2(@Body Login login);
}
