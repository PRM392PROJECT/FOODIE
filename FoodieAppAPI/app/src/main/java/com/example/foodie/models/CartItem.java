package com.example.foodie.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int  cartItemId;
    private int cartId;
    private int productId;
    private int restaurantId;
    private String productName;
    private String productImageUrl;
    private int quantity;
    private double price;
    private String createAt;
    private String upDateAt;

    public CartItem() {
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpDateAt() {
        return upDateAt;
    }

    public void setUpDateAt(String upDateAt) {
        this.upDateAt = upDateAt;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", restaurantId=" + restaurantId +
                ", productName='" + productName + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", createAt='" + createAt + '\'' +
                ", upDateAt='" + upDateAt + '\'' +
                '}';
    }
}
