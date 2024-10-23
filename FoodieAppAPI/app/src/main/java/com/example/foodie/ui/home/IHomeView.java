package com.example.foodie.ui.home;

import com.example.foodie.models.Category;
import com.example.foodie.models.Product;

import java.util.List;

public interface IHomeView {
    void loadTablayout(List<Category> categoryFoods);
    void searchFood(String title);
    void showFoods(List<Product> products);
    void showLoadingFood();
    void hideLoadingFood();
}
