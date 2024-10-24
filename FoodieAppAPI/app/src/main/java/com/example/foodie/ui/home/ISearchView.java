package com.example.foodie.ui.home;

import com.example.foodie.models.Product;

import java.util.List;

public interface ISearchView {
    void showProducts(List<Product> products);
    void showLoading();
    void hideLoading();
    void showEmpty();
}
