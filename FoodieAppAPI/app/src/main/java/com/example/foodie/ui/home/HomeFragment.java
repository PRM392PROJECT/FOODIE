package com.example.foodie.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.customview.CollectionPagerAdapter;
import com.example.foodie.databinding.FragmentHomeBinding;
import com.example.foodie.models.Category;
import com.example.foodie.models.Product;
import com.example.foodie.ui.product.ProductListFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView , View.OnClickListener,View.OnKeyListener{
    private HomePresenter presenter;
    private FragmentHomeBinding binding;
    private List<Fragment> fragments;
    private boolean isLoadData = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenter(this,getContext());
        fragments = new ArrayList<>();
        binding.header.iconSearch.setOnClickListener(this);
        binding.header.searchEditText.setOnKeyListener(this);
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // Lưu trạng thái của TabLayout
//        int selectedTabPosition = binding.tabFoodCategory.getSelectedTabPosition();
//        outState.putInt("selectedTabPosition", selectedTabPosition);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void loadTablayout(List<Category> categoryFoods) {
        requireActivity().runOnUiThread(()->{
            fragments.clear();
            for (Category categoryFood : categoryFoods) {
                ProductListFragment fragment = ProductListFragment.newInstance(categoryFood.getCategoryId());
                fragments.add(fragment);
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
                        ProductListFragment fragment = (ProductListFragment) fragments.get(tab.getPosition());
                        if(!fragment.isLoadData()){
                            fragment.loadFoods();
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
                    ProductListFragment fragment = (ProductListFragment) fragments.get(tab.getPosition());
                    fragment.loadFoods();
                }
            });
        });
    }

    @Override
    public void searchFood(String title) {

    }

    @Override
    public void showFoods(List<Product> products) {
        requireActivity().runOnUiThread(() -> {
            int selectedTabPosition = binding.tabFoodCategory.getSelectedTabPosition();
            if (selectedTabPosition >= 0 && selectedTabPosition < fragments.size()) {
                ProductListFragment fragment = (ProductListFragment) fragments.get(selectedTabPosition);
                fragment.showFoods(products);
            } else {
                Log.e("HomeFragment", "Selected tab position is out of bounds");
            }
        });
    }

    @Override
    public void showLoadingFood() {
        requireActivity().runOnUiThread(()->{
            ProductListFragment fragment = (ProductListFragment) fragments.get(binding.tabFoodCategory.getSelectedTabPosition());
            fragment.showLoadingFood();
        });
    }

    @Override
    public void hideLoadingFood() {
        requireActivity().runOnUiThread(()->{
            ProductListFragment fragment = (ProductListFragment) fragments.get(binding.tabFoodCategory.getSelectedTabPosition());
            fragment.hideLoadingFood();
        });
    }

    public void reset() {
        Thread thread = new Thread(() -> {
            presenter.getFoodCategory();
            requireActivity().runOnUiThread(() -> {
                int selectedTabPosition = binding.tabFoodCategory.getSelectedTabPosition();
                if (selectedTabPosition >= 0 && selectedTabPosition < fragments.size()) {
                    ProductListFragment fragment = (ProductListFragment) fragments.get(selectedTabPosition);
                    fragment.loadFoods();
                }
            });
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void loadData(){
       if(presenter == null){
           presenter = new HomePresenter(this,getContext());
       }
        if(!isLoadData){
            presenter.getFoodCategory();
            isLoadData = true;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.header.iconSearch.getId()){
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
            return  true;
        }
        return false;
    }
}