package com.example.foodie.ui.cart;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.foodie.R;
import com.example.foodie.models.CartItem;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;

    public CartAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem currentItem = cartItemList.get(position);
        // Bind data to the views in holder
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        // Define the views in the item layout
        // Example: TextView itemName, TextView itemPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            // Example: itemName = itemView.findViewById(R.id.itemName);
            // itemPrice = itemView.findViewById(R.id.itemPrice);
        }

        public void bind(CartItem item) {
            // Bind the data to the views
            // Example: itemName.setText(item.getName());
            // itemPrice.setText(String.valueOf(item.getPrice()));
        }
    }
}

