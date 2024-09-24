package com.example.foodie.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.foodie.R;
import com.example.foodie.databinding.ActivitySearchBinding;
import com.example.foodie.models.Food;
import com.example.foodie.presenters.home.ISearchView;
import com.example.foodie.presenters.home.SearchPresenter;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements ISearchView {
    private static final String TITLE_SEARCH = "title";
    private ActivitySearchBinding binding;
    private FoodListFragment foodListFragment;
    private SearchPresenter presenter;

    public static void startActivity(Context context, String title) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(TITLE_SEARCH, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        try {
            // Sử dụng ViewBinding để gắn kết layout
            binding = ActivitySearchBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            presenter = new SearchPresenter(this);
            foodListFragment = new FoodListFragment();

            String title = getIntent().getStringExtra(TITLE_SEARCH);
            binding.title.setText(title);


            // Xử lý sự kiện nút quay lại
            binding.btnBack.setOnClickListener(v -> finish());
            // set list food layout
            getSupportFragmentManager().beginTransaction()
                            .add(binding.listFood.getId(),foodListFragment).commit();

            presenter.searchFoodByTitle(title);

        } catch (Exception ex) {
            Log.e("SearchResultActivity", "Error creating activity: " + ex.getMessage());
        }
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void notFoundFoods() {

    }

    @Override
    public void showFoods(List<Food> foods) {
        foodListFragment.showFoods(foods);
    }
}
