package com.example.foodie.models;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private int cartId;
    private int userId;
    private String createdAt;
    private List<CartItem> cartItems;

    public Cart() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", createdAt='" + createdAt + '\'' +
                ", cartItems=" + cartItems +
                '}';
    }
}
