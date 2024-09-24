package com.example.foodie.models;

public class FoodImage {
    private int imageId;
    private Food food;  // Thay vì lưu foodId, sử dụng đối tượng Food
    private String imageUrl;

    // Constructor rỗng
    public FoodImage() {}

    // Constructor với tham số
    public FoodImage(int imageId, Food food, String imageUrl) {
        this.imageId = imageId;
        this.food = food;
        this.imageUrl = imageUrl;
    }

    // Getter và Setter
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
