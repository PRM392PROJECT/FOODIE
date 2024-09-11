package com.example.foodie.presenters;

import com.example.foodie.R;
import com.example.foodie.models.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodPresenter {
    public List<Food> getFoods(int count) {
        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            foodList.add(new Food("Veggie tomato mix", "N1,900"));
        }
        return foodList;
    }
}
