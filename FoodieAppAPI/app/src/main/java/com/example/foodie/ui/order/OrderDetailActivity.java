package com.example.foodie.ui.order;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivityOrderDetailBinding;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener, IOrderView {
    private ActivityOrderDetailBinding binding;
    private OrderPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
<<<<<<< Updated upstream
        presenter = new OrderPresenter(this,this);
        binding.btnBack.setOnClickListener(this);
        binding.btnPlaceOrder.setOnClickListener(this);
=======

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
>>>>>>> Stashed changes
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnBack.getId()) {
            finish();
<<<<<<< Updated upstream
        }else if(v.getId() == binding.btnPlaceOrder.getId()){
            presenter.order(1,1);
=======
        } else if (v.getId() == binding.btnOrder.getId()) {
            orderNow();
>>>>>>> Stashed changes
        }
    }

    @Override
    public void orderSuccess() {
<<<<<<< Updated upstream

=======
        Intent intent = new Intent(this, OrderSuccessActivity.class);
        startActivity(intent);
        setResult(RESULT_OK); // Set result code to indicate success
        finish();
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
}
=======

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
>>>>>>> Stashed changes
