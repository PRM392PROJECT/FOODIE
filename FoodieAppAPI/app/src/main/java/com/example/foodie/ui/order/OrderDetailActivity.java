package com.example.foodie.ui.order;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener,IOrderView {
    private ActivityOrderDetailBinding binding;
    private OrderPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new OrderPresenter(this,this);
        binding.btnBack.setOnClickListener(this);
        binding.btnPlaceOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnBack.getId()){
            finish();
        }else if(v.getId() == binding.btnPlaceOrder.getId()){
            presenter.order(1,1);
        }
    }

    @Override
    public void orderSuccess() {

    }

    @Override
    public void orderFailed(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }
}