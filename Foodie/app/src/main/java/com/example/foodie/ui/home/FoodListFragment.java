package com.example.foodie.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.R;
import com.example.foodie.databinding.FoodListLayoutBinding;
import com.example.foodie.models.Food;
import com.example.foodie.ui.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration; // Import thêm cho việc xử lý cấu hình

public class FoodListFragment extends Fragment {
    private FoodListLayoutBinding binding;
    private int numColumns;
    private FoodAdapter adapter;

    public FoodListFragment() {
    }

    public static FoodListFragment newInstance() {
        return new FoodListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FoodListLayoutBinding.inflate(inflater, container, false);
        adapter = new FoodAdapter(getContext(), new ArrayList<>());
        binding.listFood.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateNumColumns(); // Cập nhật số lượng cột ban đầu
        binding.scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // Kiểm tra nếu đã cuộn đến cuối của ScrollView
                View child = binding.scrollView.getChildAt(0);
                if (child != null) {
                    int childHeight = child.getMeasuredHeight();
                    int scrollViewHeight = binding.scrollView.getMeasuredHeight();
                    if (scrollY >= (childHeight - scrollViewHeight)) {
                        // Load thêm dữ liệu nếu cần
                    }
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateNumColumns();
    }

    private void updateNumColumns() {
        // Lấy chiều rộng màn hình và tính toán số cột
        int screenWidth = ScreenUtils.getScreenWidth(getContext());
        int itemWidth = getResources().getDimensionPixelSize(R.dimen.food_item_width);
        numColumns = screenWidth / itemWidth;

        // Cập nhật số cột cho NoScrollGridView
        if (binding.listFood != null) {
            binding.listFood.setNumColumns(numColumns);
        }
    }

    public void showFoods(List<Food> foods) {
        adapter.setData(foods);
    }

    public void showLoadingFood() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoadingFood() {
        binding.progressBar.setVisibility(View.GONE);
    }

    public boolean isFoodsEmpty() {
        return adapter.isDataEmpty();
    }
}
