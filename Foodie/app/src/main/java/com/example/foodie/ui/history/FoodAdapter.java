package com.example.foodie.ui.history;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodie.R;
import com.example.foodie.models.FoodItem;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodItems;
    private Context context;

    public FoodAdapter(List<FoodItem> foodItems, Context context) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_product_layout, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem currentFoodItem = foodItems.get(position);

        holder.tvFoodName.setText(currentFoodItem.getFoodName());

        holder.tvPrice.setText(currentFoodItem.getPrice());

        holder.imgFood.setImageResource(currentFoodItem.getImageResId());

        holder.tvDescription.setText("Description here");
    }
    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvFoodName, tvDescription, tvPrice;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
