// Generated by view binder compiler. Do not edit!
package com.example.foodie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.foodie.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class OrderDetailCartBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button btnRate;

  @NonNull
  public final Button btnReOrder;

  @NonNull
  public final ConstraintLayout categoryStatusContainer;

  @NonNull
  public final TextView foodCategory;

  @NonNull
  public final ImageView foodImage;

  @NonNull
  public final TextView foodname;

  @NonNull
  public final TextView foodprice;

  @NonNull
  public final TextView orderDetails;

  @NonNull
  public final TextView orderStatus;

  private OrderDetailCartBinding(@NonNull CardView rootView, @NonNull Button btnRate,
      @NonNull Button btnReOrder, @NonNull ConstraintLayout categoryStatusContainer,
      @NonNull TextView foodCategory, @NonNull ImageView foodImage, @NonNull TextView foodname,
      @NonNull TextView foodprice, @NonNull TextView orderDetails, @NonNull TextView orderStatus) {
    this.rootView = rootView;
    this.btnRate = btnRate;
    this.btnReOrder = btnReOrder;
    this.categoryStatusContainer = categoryStatusContainer;
    this.foodCategory = foodCategory;
    this.foodImage = foodImage;
    this.foodname = foodname;
    this.foodprice = foodprice;
    this.orderDetails = orderDetails;
    this.orderStatus = orderStatus;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static OrderDetailCartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static OrderDetailCartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.order_detail_cart, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static OrderDetailCartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnRate;
      Button btnRate = ViewBindings.findChildViewById(rootView, id);
      if (btnRate == null) {
        break missingId;
      }

      id = R.id.btnReOrder;
      Button btnReOrder = ViewBindings.findChildViewById(rootView, id);
      if (btnReOrder == null) {
        break missingId;
      }

      id = R.id.categoryStatusContainer;
      ConstraintLayout categoryStatusContainer = ViewBindings.findChildViewById(rootView, id);
      if (categoryStatusContainer == null) {
        break missingId;
      }

      id = R.id.foodCategory;
      TextView foodCategory = ViewBindings.findChildViewById(rootView, id);
      if (foodCategory == null) {
        break missingId;
      }

      id = R.id.foodImage;
      ImageView foodImage = ViewBindings.findChildViewById(rootView, id);
      if (foodImage == null) {
        break missingId;
      }

      id = R.id.foodname;
      TextView foodname = ViewBindings.findChildViewById(rootView, id);
      if (foodname == null) {
        break missingId;
      }

      id = R.id.foodprice;
      TextView foodprice = ViewBindings.findChildViewById(rootView, id);
      if (foodprice == null) {
        break missingId;
      }

      id = R.id.orderDetails;
      TextView orderDetails = ViewBindings.findChildViewById(rootView, id);
      if (orderDetails == null) {
        break missingId;
      }

      id = R.id.orderStatus;
      TextView orderStatus = ViewBindings.findChildViewById(rootView, id);
      if (orderStatus == null) {
        break missingId;
      }

      return new OrderDetailCartBinding((CardView) rootView, btnRate, btnReOrder,
          categoryStatusContainer, foodCategory, foodImage, foodname, foodprice, orderDetails,
          orderStatus);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
