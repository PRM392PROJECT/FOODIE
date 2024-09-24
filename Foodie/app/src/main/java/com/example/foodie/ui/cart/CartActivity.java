package com.example.foodie.ui.cart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivityCartBinding;
import com.example.foodie.models.CartItem;
import com.example.foodie.presenters.cart.CartPresenter;
import com.example.foodie.presenters.cart.ICartView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements ICartView {

    private ActivityCartBinding binding;
    private CartPresenter presenter;
    private List<CartItem> cartItems = new ArrayList<>();
    private CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Nếu không cần, có thể xóa dòng này
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Khởi tạo presenter
        presenter = new CartPresenter(this);
        for (int i = 0; i < 10; i++) {
            CartItem cartItem = new CartItem();
            cartItems.add(cartItem);
        }
        // Thiết lập RecyclerView
        adapter = new CartAdapter(cartItems);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // Thêm ItemTouchHelper để xử lý vuốt
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true; // Không hỗ trợ kéo thả
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Xóa item tại vị trí vuốt
                int position = viewHolder.getAdapterPosition();
                cartItems.remove(position);
                adapter.notifyItemRemoved(position);
                // presenter.deleteCartItem(position); // Nếu cần thực hiện xóa từ server
            }


        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);

        // Tải các item giỏ hàng
        // presenter.loadCartItems();
    }

    @Override
    public void delete() {
        // Xử lý khi xóa
    }

    @Override
    public void showCart(List<CartItem> carts) {
        // Hiển thị danh sách giỏ hàng
        cartItems.clear();
        cartItems.addAll(carts);
        adapter.notifyDataSetChanged();
    }
}
