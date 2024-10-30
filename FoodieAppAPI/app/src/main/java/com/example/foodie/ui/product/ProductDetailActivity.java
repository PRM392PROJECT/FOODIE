package com.example.foodie.ui.product;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

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

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, IProductDetailView, SeekBar.OnSeekBarChangeListener {

    private static final String EXTRA_PRODUCT = "extra_productId";
    private ActivityProductDetailBinding binding;
    private int foodId;
    private FeedBackAdapter adapter;
    private ProductDetailPresenter presenter;
<<<<<<< Updated upstream
    private int numberFeedback=3;
    private int pageFeedback=1;

    public ProductDetailActivity(){
        presenter = new ProductDetailPresenter(this);
=======
    private int numberFeedback = 3;
    private int pageFeedback = 1;
    private Product product;

    public ProductDetailActivity() {
>>>>>>> Stashed changes
    }

    public static Intent newIntent(Context context, int foodId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, foodId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
        EdgeToEdge.enable(this);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(this);
        binding.buttonAddToCart.setOnClickListener(this);
        binding.buttonOrder.setOnClickListener(this);
        adapter = new FeedBackAdapter(new ArrayList<>(), this);
        binding.listviewFeedback.setAdapter(adapter);
        foodId = (Integer) getIntent().getSerializableExtra(EXTRA_PRODUCT);
=======
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ProductDetailPresenter(this, this);
        initUI();

        foodId = getIntent().getIntExtra(EXTRA_PRODUCT, 0);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        }if(v.getId() == binding.buttonAddToCart.getId()){
        }
        if(v.getId() == binding.buttonOrder.getId()){
=======
        } else if (v == binding.buttonAddToCart) {
            // Add to Cart functionality here
        } else if (v == binding.buttonOrder) {
>>>>>>> Stashed changes
            showOrder();
        }
    }

    @Override
    public void loadFoodDetail(Product product) {
        if (product != null) {
            binding.productPrice.setText(String.format("%s$", product.getPrice()));
<<<<<<< Updated upstream
            binding.productName.setText(String.format("%s", product.getName()));
            binding.productDescription.setText(String.format("%s", product.getDescription()));
=======
            binding.productName.setText(product.getName());
            binding.productDescription.setText(product.getDescription());
            Glide.with(this)
                    .load(product.getImages().isEmpty() ? "" : product.getImages().get(0).getImageUrl())
                    .placeholder(R.drawable.ic_food)
                    .error(R.drawable.ic_food)
                    .into(binding.productImages);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        if(user !=null){
            Intent intent = new Intent(this, OrderDetailActivity.class);
            startActivity(intent);
        }
        else{
            // Hiển thị thông báo yêu cầu đăng nhập
=======
        if (user != null) {
            Order order = createOrder(user);
            Intent intent = OrderDetailActivity.newIntent(this, order);
            startActivityForResult(intent, 100);
        } else {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
        } else if (requestCode == 100 && resultCode == OrderDetailActivity.RESULT_OK && data != null) {
            String orderStatus = data.getStringExtra("orderStatus");
            if ("success".equals(orderStatus)) {
                finish();
            }
>>>>>>> Stashed changes
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Handle quantity change if needed
    }

<<<<<<< Updated upstream
}
=======
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
        // Display cart functionality if implemented
    }
}
>>>>>>> Stashed changes
