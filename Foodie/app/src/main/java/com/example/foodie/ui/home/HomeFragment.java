package com.example.foodie.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.customview.CollectionPagerAdapter;
import com.example.foodie.databinding.FragmentHomeBinding;
import com.example.foodie.models.CategoryFood;
import com.example.foodie.models.Food;
import com.example.foodie.presenters.home.HomePresenter;
import com.example.foodie.presenters.home.IHomeView;
import com.example.foodie.ui.cart.CartActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView {
    private HomePresenter presenter;
    private FragmentHomeBinding binding;
    private List<Fragment> fragments;
    public HomeFragment() {
        // Constructor mặc định
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout và khởi tạo presenter
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        presenter = new HomePresenter(this);
        fragments = new ArrayList<>();
        presenter.getFoodCategory();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.header.iconSearch.setOnClickListener(v ->{
            String title = binding.header.searchEditText.getHint().toString();
            if(!title.isBlank()){
                searchFood(title);
            }
        });
        binding.header.searchEditText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                String title = binding.header.searchEditText.getText().toString();
                if (!title.isBlank()) {
                    searchFood(title);
                }
                return true;
            }
            return false;
        });
        binding.header.iconCart.setOnClickListener(V->{
            Intent intent = new Intent(getContext(), CartActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Lưu trạng thái của TabLayout
        int selectedTabPosition = binding.tabFoodCategory.getSelectedTabPosition();
        outState.putInt("selectedTabPosition", selectedTabPosition);
    }


    @Override
    public void loadTablayout(List<CategoryFood> categoryFoods) {
        for (CategoryFood categoryFood : categoryFoods) {
            fragments.add(new FoodListFragment());
        }

        // Tạo adapter cho ViewPager2
        CollectionPagerAdapter viewPagerAdapter = new CollectionPagerAdapter(requireActivity(), fragments);
        binding.viewPagerFood.setAdapter(viewPagerAdapter);

        // Đặt OffscreenPageLimit dựa trên số lượng fragment
        binding.viewPagerFood.setOffscreenPageLimit(fragments.size());

        // Kết nối TabLayout với ViewPager2
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabFoodCategory, binding.viewPagerFood, (tab, position) -> {
            tab.setText(categoryFoods.get(position).getCategoryName()); // Đặt tên danh mục cho mỗi tab
        });
        tabLayoutMediator.attach();
        // load foods for the first tab
        binding.tabFoodCategory.post(() -> {
            binding.tabFoodCategory.selectTab(binding.tabFoodCategory.getTabAt(0));
        });
        binding.tabFoodCategory.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               try {
                   FoodListFragment fragment = (FoodListFragment) fragments.get(tab.getPosition());
                   if(fragment.isFoodsEmpty()){
                       presenter.getFoodByCategory(tab.getPosition());
                   }
               }catch (Exception ex){
                   Log.e("HomeFragment", "Error loading foods: " + ex.getMessage());
               }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                FoodListFragment foodListFragment = (FoodListFragment) fragments.get(tab.getPosition());
                if(foodListFragment.isFoodsEmpty()){
                    presenter.getFoodByCategory(tab.getPosition());
                }
            }
        });
    }


    @Override
    public void searchFood(String title) {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @Override
    public void showFoods(List<Food> foods) {
        requireActivity().runOnUiThread(()->{
            FoodListFragment fragment = (FoodListFragment) fragments.get(binding.tabFoodCategory.getSelectedTabPosition());
            fragment.showFoods(foods);
        });
    }

    @Override
    public void showLoadingFood() {
        requireActivity().runOnUiThread(()->{
            FoodListFragment fragment = (FoodListFragment) fragments.get(binding.tabFoodCategory.getSelectedTabPosition());
            fragment.showLoadingFood();
        });
    }

    @Override
    public void hideLoadingFood() {
        requireActivity().runOnUiThread(()->{
            FoodListFragment fragment = (FoodListFragment) fragments.get(binding.tabFoodCategory.getSelectedTabPosition());
            fragment.hideLoadingFood();
        });
    }
}
