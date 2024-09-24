package com.example.foodie.presenters.home;

import com.example.foodie.models.Food;

import java.util.List;

public interface ISearchView {
    void showLoading();
    void hideLoading();
    void notFoundFoods();
    void showFoods(List<Food> foods);

}
