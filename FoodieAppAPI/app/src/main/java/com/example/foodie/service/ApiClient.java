package com.example.foodie.service;

import android.content.Context;

import com.example.foodie.R;
import com.example.foodie.models.Token;
import com.example.foodie.untils.UserInfoManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    // Hàm lấy Retrofit đã thêm Interceptor cho JWT token
    public static Retrofit getClient(Context context) {
        String BASE_URL = context.getString(R.string.BASE_URL_API);
        Token token = UserInfoManager.getToken(context);
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(token.getTokenString())) // Thêm Interceptor cho JWT token
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client) // Đính kèm OkHttpClient với Interceptor
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // Nếu không cần token, bạn có thể sử dụng phiên bản không Interceptor
    public static Retrofit getClientWithoutToken(Context context) {
        String BASE_URL = context.getString(R.string.BASE_URL_API);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
