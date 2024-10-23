package com.example.foodie.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivitySearchBinding;
import com.example.foodie.ui.product.ProductListFragment;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener,ISearchView{

    private ActivitySearchBinding binding;
    private SearchProductPresenter  presenter;
    private ProductListFragment productListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new SearchProductPresenter(this,this);
        productListFragment = new ProductListFragment();
        binding.btnBack.setOnClickListener(this);
        setFragment();
        productListFragment.searchFoods("com");
    }

    @Override
    public void onClick(View v) {

    }
    private void setFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listFood,productListFragment)
                .commit();
    }
}