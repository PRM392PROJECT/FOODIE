package com.example.foodie.ui.product;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.foodie.R;
import com.example.foodie.databinding.ActivityProductDetailBinding;
import com.example.foodie.models.Feedback;
import com.example.foodie.models.Order;
import com.example.foodie.models.OrderItem;
import com.example.foodie.models.Product;
import com.example.foodie.models.User;
import com.example.foodie.ui.authen.AuthenActivity;
import com.example.foodie.ui.cart.CartActivity;
import com.example.foodie.ui.order.OrderDetailActivity;
import com.example.foodie.untils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, IProductDetailView, SeekBar.OnSeekBarChangeListener {

    private static final String EXTRA_PRODUCT = "extra_productId";
    private ActivityProductDetailBinding binding;
    private int foodId;
    private FeedBackAdapter adapter;
    private ProductDetailPresenter presenter;
    private int numberFeedback = 3;
    private int pageFeedback = 1;
    private Product product;

    public ProductDetailActivity() {
    }

    public static Intent newIntent(Context context, int foodId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, foodId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ProductDetailPresenter(this, this);
        initUI();

        foodId = getIntent().getIntExtra(EXTRA_PRODUCT, 0);
        if (foodId == 0) {
            finish();
            return;
        }

        presenter.loadFoodDetail(foodId);
        presenter.loadFeedbacks(foodId, pageFeedback, numberFeedback);
    }

    private void initUI() {
        binding.btnBack.setOnClickListener(this);
        binding.buttonAddToCart.setOnClickListener(this);
        binding.buttonOrder.setOnClickListener(this);
        binding.seekBarQuantity.setOnSeekBarChangeListener(this);

        adapter = new FeedBackAdapter(new ArrayList<>(), this);
        binding.listviewFeedback.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnBack) {
            finish();
        } else if (v == binding.buttonAddToCart) {
<<<<<<< Updated upstream
            // Add to Cart functionality here
=======
            User user = UserInfoManager.getUserInfo(this);
            if (user != null) {
                // Nếu đã đăng nhập, thêm sản phẩm vào giỏ hàng
                int quantity = binding.seekBarQuantity.getProgress();
                int productId = product.getProductId();
                double price = product.getPrice();
                presenter.addTocart(productId, quantity, price);
            } else {
                // Nếu chưa đăng nhập, hiển thị thông báo đăng nhập
                showLoginDialog(this);
            }
>>>>>>> Stashed changes
        } else if (v == binding.buttonOrder) {
            showOrder();
        }
    }

    @Override
    public void loadFoodDetail(Product product) {
        this.product = product;
        if (product != null) {
            binding.productPrice.setText(String.format("%s$", product.getPrice()));
            binding.productName.setText(product.getName());
            binding.productDescription.setText(product.getDescription());
            Glide.with(this)
                    .load(product.getImages().isEmpty() ? "" : product.getImages().get(0).getImageUrl())
                    .placeholder(R.drawable.ic_food)
                    .error(R.drawable.ic_food)
                    .into(binding.productImages);
        }
    }

    @Override
    public void loadFeedbacks(List<Feedback> feedbacks) {
        adapter.updateData(feedbacks);
    }

    @Override
    public void hideLoadingFeedback() {
        // Hide loading feedback if implemented
    }

    @Override
    public void showError(String message) {
        // Handle error display
    }

    @Override
    public void showOrder() {
        User user = UserInfoManager.getUserInfo(this);
        if (user != null) {
            Order order = createOrder(user);
            Intent intent = OrderDetailActivity.newIntent(this, order);
            startActivityForResult(intent, 100);
        } else {
            showLoginDialog(this);
        }
    }

    private Order createOrder(User user) {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        order.setUserId(user.getUserId());
        order.setRestaurantId(product.getRestaurantId());
        orderItem.setProduct(product);
        orderItem.setQuantity(binding.seekBarQuantity.getProgress()); // set quantity from SeekBar
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
        return order;
    }

    private void showLoginDialog(Context context) {
        new AlertDialog.Builder(this)
                .setTitle("Login Required")
                .setMessage("You need to login before placing an order. Do you want to login now?")
                .setPositiveButton("Login", (dialog, which) -> {
                    Intent intent = new Intent(context, AuthenActivity.class);
                    startActivityForResult(intent, 100);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == AuthenActivity.RESULT_OK) {
            showOrder();
        } else if (requestCode == 100 && resultCode == OrderDetailActivity.RESULT_OK && data != null) {
            String orderStatus = data.getStringExtra("orderStatus");
            if ("success".equals(orderStatus)) {
                finish();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Handle quantity change if needed
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Additional handling if needed
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Additional handling if needed
    }

    @Override
    public void showCart() {
<<<<<<< Updated upstream
        // Display cart functionality if implemented
    }
=======

    }
    @Override
    public void addTocartSuccess() {
        runOnUiThread(() -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            Log.i("add-tocart","success");
        });
    }

>>>>>>> Stashed changes
}
