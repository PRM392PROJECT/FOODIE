package com.example.foodie.ui.product;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodie.databinding.ActivityProductDetailBinding;
import com.example.foodie.models.Feedback;
import com.example.foodie.models.Product;
import com.example.foodie.models.User;
import com.example.foodie.ui.authen.AuthenActivity;
import com.example.foodie.ui.order.OrderDetailActivity;
import com.example.foodie.untils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener , IProductDetailView {

    private static final String EXTRA_PRODUCT = "extra_productId";
    private ActivityProductDetailBinding binding;
    private int foodId;
    private FeedBackAdapter adapter;
    private ProductDetailPresenter presenter;
    private int numberFeedback=3;
    private int pageFeedback=1;

    public ProductDetailActivity(){
        presenter = new ProductDetailPresenter(this);
    }
    public static Intent newIntent(Context context, int foodId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, foodId);
        return intent;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(this);
        binding.buttonAddToCart.setOnClickListener(this);
        binding.buttonOrder.setOnClickListener(this);
        adapter = new FeedBackAdapter(new ArrayList<>(), this);
        binding.listviewFeedback.setAdapter(adapter);
        foodId = (Integer) getIntent().getSerializableExtra(EXTRA_PRODUCT);
        if (foodId == 0) {
            finish();
            return;
        }
        presenter.loadFoodDetail(foodId);
        presenter.loadFeedbacks(foodId,pageFeedback,numberFeedback);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnBack.getId()){
            finish();
        }if(v.getId() == binding.buttonAddToCart.getId()){
        }
        if(v.getId() == binding.buttonOrder.getId()){
            showOrder();
        }
    }

    @Override
    public void loadFoodDetail(Product product) {
        if (product != null) {
            binding.productPrice.setText(String.format("%s$", product.getPrice()));
            binding.productName.setText(String.format("%s", product.getName()));
            binding.productDescription.setText(String.format("%s", product.getDescription()));
        }
    }

    @Override
    public void loadFeedbacks(List<Feedback> feedbacks) {
        adapter.updateData(feedbacks);
    }

    @Override
    public void hideLoadingFeedback() {

    }
    @Override
    public void showError(String message) {
    }

    @Override
    public void showOrder() {
        User user = UserInfoManager.getUserInfo(this);
        if(user !=null){
            Intent intent = new Intent(this, OrderDetailActivity.class);
            startActivity(intent);
        }
        else{
            // Hiển thị thông báo yêu cầu đăng nhập
            showLoginDialog(this);
        }
    }

    @Override
    public void showCart() {
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == AuthenActivity.RESULT_OK) {
            showOrder();
        }
    }
    private void showLoginDialog(Context context) {
        // Tạo một AlertDialog để yêu cầu đăng nhập
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login Required");
        builder.setMessage("You need to login before placing an order. Do you want to login now?");
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Chuyển hướng đến trang đăng nhập
                Intent intent = new Intent(context, AuthenActivity.class);
                startActivityForResult(intent,100);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Hủy bỏ thông báo và không cho phép đặt hàng
                dialog.dismiss();
            }
        });

        // Hiển thị AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}