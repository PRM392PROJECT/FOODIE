package com.example.foodie.ui.product;

import android.util.Log;

import com.example.foodie.models.ViewPage;
import com.example.foodie.models.Product; // Đảm bảo bạn đã import mô hình Food
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ProductListPresenter {
    private ApiService apiService;
    private IProductListView view;
    private int currentPage = 1;
    private  int pageSize = 10;
    public ProductListPresenter(IProductListView view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void getFoodByCategory(int categoryId) {
        view.showLoadingFood(); // Hiển thị loading

        Call<ViewPage<Product>> call = apiService.getFoods(categoryId, currentPage, pageSize);
        call.enqueue(new Callback<ViewPage<Product>>() {
            @Override
            public void onResponse(Call<ViewPage<Product>> call, Response<ViewPage<Product>> response) {
                view.hideLoadingFood(); // Ẩn loading
                if (response.isSuccessful() && response.body() != null) {
                    ViewPage<Product> responseData = response.body(); // Lấy phản hồi

                    // Lấy danh sách thực phẩm từ phản hồi
                    List<Product> foods = responseData.getItems();
                    if(foods.isEmpty()){
                        view.showFoodEmpty();
                    }else {
                        view.hideFoodEmpty();
                        view.showFoods(foods);
                        Log.i("FoodListPresenter", "Danh sách thực phẩm:"+foods);
                    }
                } else {
                    view.showError("Không thể tải dữ liệu thực phẩm.");
                    Log.i("FoodListPresenter", "Không thể tải dữ liệu thực phẩm.");
                }
            }

            @Override
            public void onFailure(Call<ViewPage<Product>> call, Throwable t) {
                view.hideLoadingFood(); // Ẩn loading
                view.showError("Đã xảy ra lỗi: " + t.getMessage());
            }
        });
    }

}
