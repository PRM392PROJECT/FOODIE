package com.example.foodie.models;

import java.util.List;

public class Food {
    private int foodId;
    private Restaurant restaurant;  // Thay vì lưu restaurantId, sử dụng đối tượng Restaurant
    private CategoryFood category;  // Thay vì lưu categoryId, sử dụng đối tượng CategoryFood
    private String name;
    private String description;
    private double price;
    private List<FoodImage> images;
    // Constructor rỗng
    public Food() {}

    // Constructor với tham số
    public Food(int foodId, Restaurant restaurant, CategoryFood category, String name, String description, double price) {
        this.foodId = foodId;
        this.restaurant = restaurant;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getter và Setter
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public CategoryFood getCategory() {
        return category;
    }

    public void setCategory(CategoryFood category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<FoodImage> getImages() {
        return images;
    }

    public void setImages(List<FoodImage> images) {
        this.images = images;
    }
}
