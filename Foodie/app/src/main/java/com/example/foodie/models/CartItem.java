package com.example.foodie.models;

public class CartItem {
    private int cartId;
    private Food food;
    private int quantity;
    private double totalPrice;

    public CartItem() {
        cartId = 1;
        food = new Food();
        quantity = 1;
        totalPrice = 1;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
