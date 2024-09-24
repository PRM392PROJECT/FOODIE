package com.example.foodie.models;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String location;
    private String phoneNumber;
    private String openingHours;
    private User manager;  // Thay vì lưu managerId, sử dụng đối tượng User
    private String avatarUrl;
    private String coverImageUrl;

    // Constructor rỗng
    public Restaurant() {}

    // Constructor với tham số
    public Restaurant(int restaurantId, String name, String location, String phoneNumber, String openingHours, User manager, String avatarUrl, String coverImageUrl) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.manager = manager;
        this.avatarUrl = avatarUrl;
        this.coverImageUrl = coverImageUrl;
    }

    // Getter và Setter
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
