package com.example.foodie.presenters.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.text.PrecomputedText;

import com.example.foodie.models.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPresenter {
    private ISearchView view ;
    public  SearchPresenter(ISearchView view){
        this.view = view;
    }
    @SuppressLint("StaticFieldLeak")
    public AsyncTask searchFoodByTitle(String title){
        return new AsyncTask<Void, Void, List<Food>>() {
            @Override
            protected void onPreExecute() {
                view.showLoading();
            }
            @Override
            protected List<Food> doInBackground(Void... voids) {
                List<Food> foods = new ArrayList<>();
                try {
                    Thread.sleep(2000);
                    for (int i = 0; i < 10; i++) {
                        foods.add(new Food());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return foods;
            }
            @Override
            protected void onPostExecute(List<Food> foods) {
                view.hideLoading();
                if(foods.isEmpty()){
                    view.notFoundFoods();
                }else {
                    view.showFoods(foods);
                }
            }
        }.execute();
    }
}
