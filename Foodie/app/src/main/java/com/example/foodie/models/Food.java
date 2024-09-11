package com.example.foodie.models;

public class Food {
    private String name;
    private String price;
    private String image;

    public Food(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
