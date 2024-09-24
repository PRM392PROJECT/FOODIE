package com.example.foodie.presenters.home;

import com.example.foodie.models.CategoryFood;
import com.example.foodie.models.Food;

import java.util.List;

public interface IHomeView {
    void loadTablayout(List<CategoryFood> categoryFoods);
    void searchFood(String title);
    void showFoods(List<Food> foods);
    void showLoadingFood();
    void hideLoadingFood();
}
