package com.example.foodie.ui.product;

import com.example.foodie.models.Product;

import java.util.List;

public interface IProductListView {
    void showFoods(List<Product> foods);
    void showLoadingFood();
    void hideLoadingFood();
    void showError(String s);
    void showFoodEmpty();
    void hideFoodEmpty();
}
