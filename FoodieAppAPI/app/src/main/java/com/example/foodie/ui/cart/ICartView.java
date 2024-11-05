package com.example.foodie.ui.cart;

import com.example.foodie.models.Cart;

public interface ICartView {
    void showCart(Cart cart);
    void showLoadingCart();
    void hideLoadingCart();
}
