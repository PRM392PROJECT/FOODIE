package com.example.foodie.presenters.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.foodie.models.CategoryFood;
import com.example.foodie.models.Food;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {
    private IHomeView view;
    public HomePresenter(IHomeView view){
        this.view = view;
    }
    public void getFoodCategory(){
        List<CategoryFood> categoryFoods = new ArrayList<>();
        categoryFoods.add(new CategoryFood(1,"All"));
        categoryFoods.add(new CategoryFood(2,"Pizza"));
        categoryFoods.add(new CategoryFood(3,"Burger"));
        categoryFoods.add(new CategoryFood(4,"Salad"));
        categoryFoods.add(new CategoryFood(5,"Dessert"));
        categoryFoods.add(new CategoryFood(6,"Drink"));
        categoryFoods.add(new CategoryFood(7,"Noodles"));
        categoryFoods.add(new CategoryFood(8,"Pasta"));
        categoryFoods.add(new CategoryFood(9,"Sushi"));
        categoryFoods.add(new CategoryFood(10,"Steak"));
        categoryFoods.add(new CategoryFood(11,"Hot Dog"));
        categoryFoods.add(new CategoryFood(12,"Fried Chicken"));
        categoryFoods.add(new CategoryFood(13,"Hot Pot"));
        categoryFoods.add(new CategoryFood(14,"Noodles"));
        categoryFoods.add(new CategoryFood(15,"Soup"));
        view.loadTablayout(categoryFoods);
    }
    public void searchFood(String food){

    }
    @SuppressLint("StaticFieldLeak")
    public AsyncTask getFoodByCategory(int category) {
        return new AsyncTask<Void, Void, List<Food>>() {

            @Override
            protected void onPreExecute() {
                view.showLoadingFood();
            }

            @Override
            protected List<Food> doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000); // Giả lập độ trễ xử lý
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                List<Food> foods = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Food food = new Food();
                    food.setName("Food "+i);
                    food.setPrice(9000+i);
                    foods.add(food);
                }
                return foods;
            }
            @Override
            protected void onPostExecute(List<Food> foods) {
                view.hideLoadingFood();
                view.showFoods(foods);
            }
        }.execute();
    }

}
