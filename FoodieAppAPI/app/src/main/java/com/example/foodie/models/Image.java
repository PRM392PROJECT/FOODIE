package com.example.foodie.models;

import java.io.Serializable;

public class Image implements Serializable {
    private int imageId;
    private int productId;
    private String imageUrl;
    private int orderIndex;

    public Image() {
    }

    public Image(int imageId, int productId, String imageUrl, int orderIndex) {
        this.imageId = imageId;
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.orderIndex = orderIndex;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", productId=" + productId +
                ", imageUrl='" + imageUrl + '\'' +
                ", orderIndex=" + orderIndex +
                '}';
    }
}
