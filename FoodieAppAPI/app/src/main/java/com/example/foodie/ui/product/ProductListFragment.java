package com.example.foodie.ui.product;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.foodie.databinding.FragmentProductListBinding;
import com.example.foodie.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment implements IProductListView {
    private static final String ARG_CATEGORY_ID = "categoryId";
    private FragmentProductListBinding binding;
    private ProductAdapter adapter;
    private GridView gridView;
    private List<Product> foods;
    private ProductListPresenter presenter;
    private int categoryId;
    public ProductListFragment() {
        // Required empty public constructor
    }
     public static ProductListFragment newInstance(int categoryId) {
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryId);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProductListPresenter(this);
        foods = new ArrayList<>();
        if (getArguments() != null) {
            categoryId = getArguments().getInt(ARG_CATEGORY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = binding.gridFoodList;
        foods = new ArrayList<>();
        adapter = new ProductAdapter(getContext(),foods);
        gridView.setAdapter(adapter);

        // detail food
        gridView.setOnItemClickListener((parent, getView1, position, id) -> {
            Object selectedItem = parent.getItemAtPosition(position);
            if (selectedItem instanceof Product) {
                Product selectedFood = (Product) selectedItem;
                Intent intent = ProductDetailActivity.newIntent(getContext(),selectedFood.getProductId());
                startActivity(intent);
            }
        });

    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    public boolean isFoodsEmpty(){
        return foods.isEmpty();
    }

    public void loadFoods(){
        presenter.getFoodByCategory(categoryId);
    }

    public void showFoods(List<Product> foods) {
        adapter.updateData(foods);
    }

    public void showLoadingFood() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoadingFood() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFoodEmpty() {
        binding.foodEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFoodEmpty() {
        binding.foodEmpty.setVisibility(View.GONE);
    }
}