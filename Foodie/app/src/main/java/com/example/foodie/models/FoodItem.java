package com.example.foodie.models;

public class FoodItem {
    private String foodName;
    private String price;
    private int imageResId;

    public FoodItem(String foodName, String price, int imageResId) {
        this.foodName = foodName;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
