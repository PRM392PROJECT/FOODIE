package com.example.foodie.ui.order;

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
import com.example.foodie.databinding.ActivityOrderDetailBinding;
import com.example.foodie.models.Order;
import com.example.foodie.models.OrderItem;
import com.example.foodie.models.User;
import com.example.foodie.untils.UserInfoManager;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener,IOrderView {
    private ActivityOrderDetailBinding binding;
    private static final String EXTRA_ORDER = "extra_order";
    private OrderPresenter presenter;
    private Order order;
    private OrderItemAdapter adapter;

    public static Intent newIntent(Context context, Order order) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_ORDER, order);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        order = (Order) getIntent().getSerializableExtra(EXTRA_ORDER);
        showOrder();
        Log.i("Order",order.toString());
        binding.listviewOrderDetail.setAdapter(adapter);
        presenter = new OrderPresenter(this,this);
        binding.btnBack.setOnClickListener(this);
        binding.btnOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnBack.getId()){
            finish();
        }else if(v.getId() == binding.btnOrder.getId()){
            orderNow();
        }
    }

    @Override
    public void orderSuccess() {
        Intent intent = new Intent(this, OrderSuccessActivity.class);
        startActivity(intent);
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
    @Override
    public void showOrder(){
        User user = UserInfoManager.getUserInfo(this);
        if(order!=null){
            double totalPay =0.0;
            for(OrderItem item : order.getOrderItems()){
                totalPay +=(item.getProduct().getPrice()*item.getQuantity());
            }
            binding.totalItem.setText(String.format("Total (%d items)",order.getOrderItems().size()));
            binding.totalPay.setText(totalPay+"đ");
            order.setTotalAmount(totalPay);
            adapter = new OrderItemAdapter(this,order.getOrderItems());
            binding.userFirstName.setText(user.getFirstName());
            binding.userLastname.setText(user.getLastName());
            binding.userPhone.setText(user.getPhoneNumber());
            binding.userAddress.setText(user.getAddress());
        }
    }
    @Override
    public void orderNow() {
        presenter.order(order);
    }
}