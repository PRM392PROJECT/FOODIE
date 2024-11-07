package com.example.foodie.ui.cart;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodie.databinding.ActivityCartBinding;
import com.example.foodie.models.Cart;

public class CartActivity extends AppCompatActivity implements View.OnClickListener ,ICartView{

    private ActivityCartBinding binding;
    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backButton.setOnClickListener(this);
        cartPresenter = new CartPresenter(this,this);
        cartAdapter = new CartAdapter(this);
        binding.recyclerViewCart.setAdapter(cartAdapter);
        cartPresenter.getCart();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.backButton.getId()){
            finish();
        }
    }

    @Override
    public void showCart(Cart cart) {
        runOnUiThread(() -> {
            cartAdapter.updateData(cart.getCartItems());
        });
    }

    @Override
    public void showLoadingCart() {

    }

    @Override
    public void hideLoadingCart() {

    }
}