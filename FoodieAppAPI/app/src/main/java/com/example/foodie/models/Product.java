package com.example.foodie.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Product implements Serializable {
    private int productId;
    private String name;
    private int categoryId;
    private int restaurantId;
    private String categoryName;
    private double price;
    private String description;
    private String createAt;
    private String  upDateAt;

    private LocalDateTime createAtDateTime;
    private  LocalDateTime updatetAtDateTime;
    private List<Image> images;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");

    public Product(){
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateAtDateTime() {
        if(createAtDateTime == null)createAtDateTime = LocalDateTime.parse(createAt, formatter);
        return createAtDateTime;
    }

    public void setCreateAtDateTime(LocalDateTime createAtDateTime) {
        this.createAtDateTime = createAtDateTime;
    }

    public LocalDateTime getUpdatetAtDateTime() {
        if(updatetAtDateTime == null)updatetAtDateTime = LocalDateTime.parse(upDateAt, formatter);
        return updatetAtDateTime;
    }

    public void setUpdatetAtDateTime(LocalDateTime updatetAtDateTime) {
        this.updatetAtDateTime = updatetAtDateTime;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Food{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createAt=" + createAtDateTime +
                ", upDateAt=" + updatetAtDateTime +
                ", images=" + images +
                '}';
    }
}
