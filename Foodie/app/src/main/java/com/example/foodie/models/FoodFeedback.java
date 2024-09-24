package com.example.foodie.models;

public class FoodFeedback {
    private int feedbackId;
    private Food food;  // Thay vì lưu foodId, sử dụng đối tượng Food
    private User user;  // Thay vì lưu userId, sử dụng đối tượng User
    private int rating;
    private String comment;
    private java.util.Date createdAt;

    // Constructor rỗng
    public FoodFeedback() {}

    // Constructor với tham số
    public FoodFeedback(int feedbackId, Food food, User user, int rating, String comment, java.util.Date createdAt) {
        this.feedbackId = feedbackId;
        this.food = food;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    // Getter và Setter
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }
}
