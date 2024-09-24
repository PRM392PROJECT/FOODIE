package com.example.foodie.models;

public class CategoryFood {
    private int categoryId;
    private String categoryName;

    // Constructor rỗng
    public CategoryFood() {}

    // Constructor với tham số
    public CategoryFood(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getter và Setter
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
