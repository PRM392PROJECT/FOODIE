package com.example.foodie.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodie.R;
import com.example.foodie.databinding.FragmentHomeBinding;
import com.example.foodie.models.Food;
import com.example.foodie.presenters.FoodPresenter;

import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        List<Food> foods = new FoodPresenter().getFoods(10);
        FoodAdapter adapter = new FoodAdapter(getContext(), foods, R.layout.food_item_layout);
        binding.listFood.setAdapter(adapter);
        return binding.getRoot();
    }
}