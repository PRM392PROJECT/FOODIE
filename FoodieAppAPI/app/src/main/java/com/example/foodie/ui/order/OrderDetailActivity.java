package com.example.foodie.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivityOrderDetailBinding;
import com.example.foodie.models.Order;
import com.example.foodie.models.OrderItem;
import com.example.foodie.models.User;
import com.example.foodie.untils.UserInfoManager;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener, IOrderView {
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
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        order = (Order) getIntent().getSerializableExtra(EXTRA_ORDER);
        if (order == null) {
            Log.e("OrderDetailActivity", "Order is null");
            finish();
            return;
        }

        presenter = new OrderPresenter(this, this);
        binding.btnBack.setOnClickListener(this);
        binding.btnOrder.setOnClickListener(this);

        adapter = new OrderItemAdapter(this, new ArrayList<>());
        binding.listviewOrderDetail.setAdapter(adapter);
        showOrder();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnBack.getId()) {
            finish();
        } else if (v.getId() == binding.btnOrder.getId()) {
            orderNow();
        }
    }

    @Override
    public void orderSuccess() {
        Intent intent = new Intent(this, OrderSuccessActivity.class);
        startActivity(intent);
        setResult(RESULT_OK); // Set result code to indicate success
        finish();
    }

    @Override
    public void orderFailed(String message) {
        showError(message);
    }

    @Override
    public void showLoading() {
        // Show loading logic here
    }

    @Override
    public void hideLoading() {
        // Hide loading logic here
    }

    @Override
    public void showError(String message) {
        Log.e("OrderDetailActivity", message);
        // Show error dialog/toast here
    }

    @Override
    public void showOrder() {
        User user = UserInfoManager.getUserInfo(this);
        if (order != null && order.getOrderItems() != null) {
            double totalPay = 0;
            int totalQuantity = 0;
            for (OrderItem item : order.getOrderItems()) {
                totalPay += item.getProduct().getPrice() * item.getQuantity();
                totalQuantity += item.getQuantity();
            }
            binding.totalItem.setText(String.format("Total (%d items)", totalQuantity));
            binding.totalPay.setText(totalPay + "đ");
            order.setTotalAmount(totalPay);

            if (user != null) {
                binding.userFirstName.setText(user.getFirstName());
                binding.userLastname.setText(user.getLastName());
                binding.userPhone.setText(user.getPhoneNumber());
                binding.userAddress.setText(user.getAddress());
            }
            adapter.updateData(order.getOrderItems());
        }
    }

    @Override
    public void orderNow() {
        presenter.order(order);
    }
}
