package com.example.foodie.ui.product;

import com.example.foodie.models.Feedback;
import com.example.foodie.models.Product;

import java.util.List;

public interface IProductDetailView {
    void loadFoodDetail(Product product);
    void loadFeedbacks(List<Feedback> feedbacks);
    void hideLoadingFeedback();
    void showError(String message);
    void showOrder();
    void showCart();
}
