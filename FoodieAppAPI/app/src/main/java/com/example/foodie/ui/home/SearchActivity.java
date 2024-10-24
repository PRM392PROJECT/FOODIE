package com.example.foodie.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivitySearchBinding;
import com.example.foodie.models.Product;
import com.example.foodie.ui.product.ProductAdapter;
import com.example.foodie.ui.product.ProductDetailActivity;
import com.example.foodie.ui.product.ProductListFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, ISearchView {

    private static final String ARG_TITLE = "title";
    private ActivitySearchBinding binding;
    private SearchProductPresenter presenter;
    private String title;
    private ProductAdapter adapter;
    public static Intent newInstance(Context context, String title) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(ARG_TITLE, title);  // Đưa title vào Intent
        return intent;
    }
    public SearchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new SearchProductPresenter(this, this);
        adapter = new ProductAdapter(this,new ArrayList<Product>());
        binding.gridFoodList.setAdapter(adapter);
        binding.btnBack.setOnClickListener(this);
        title = getIntent().getStringExtra(ARG_TITLE);
        binding.title.setText(title);
        try{
            presenter.searchFoodByName(title);
        }catch (Exception ex){
            Log.e("ErrorFood",ex.getMessage());
        }
        binding.gridFoodList.setOnItemClickListener((parent, getView1, position, id) -> {
            Object selectedItem = parent.getItemAtPosition(position);
            if (selectedItem instanceof Product) {
                try{
                    Product selectedFood = (Product) selectedItem;
                    Intent intent = ProductDetailActivity.newIntent(this,selectedFood.getProductId());
                    startActivity(intent);
                }catch(Exception ex){
                    Log.e("False detail" , ex.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnBack) {
            finish();  // Quay lại khi nhấn nút Back
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_TITLE, title);  // Lưu title khi Activity bị tạm dừng
    }

    @Override
    public void showProducts(List<Product> products) {
        adapter.updateData(products);
        binding.gridFoodList.setVisibility(View.VISIBLE);
        binding.listEmpty.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        runOnUiThread(()->{
            binding.progressBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        runOnUiThread(()->{
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public void showEmpty() {
        runOnUiThread(()->{
            binding.listEmpty.setVisibility(View.VISIBLE);
            binding.gridFoodList.setVisibility(View.GONE);
        });
    }
}
