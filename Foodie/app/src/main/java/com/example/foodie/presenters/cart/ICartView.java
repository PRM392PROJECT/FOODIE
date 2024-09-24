package com.example.foodie.presenters.cart;

import com.example.foodie.models.CartItem;

import java.util.List;

public interface ICartView {
    void delete();
    void showCart(List<CartItem> carts);
}
