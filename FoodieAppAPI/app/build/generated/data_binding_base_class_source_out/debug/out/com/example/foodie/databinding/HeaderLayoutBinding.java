// Generated by view binder compiler. Do not edit!
package com.example.foodie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

public final class HeaderLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView iconCart;

  @NonNull
  public final ImageView iconMenu;

  @NonNull
  public final ImageView iconSearch;

  @NonNull
  public final CardView searchCard;

  @NonNull
  public final EditText searchEditText;

  private HeaderLayoutBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView iconCart,
      @NonNull ImageView iconMenu, @NonNull ImageView iconSearch, @NonNull CardView searchCard,
      @NonNull EditText searchEditText) {
    this.rootView = rootView;
    this.iconCart = iconCart;
    this.iconMenu = iconMenu;
    this.iconSearch = iconSearch;
    this.searchCard = searchCard;
    this.searchEditText = searchEditText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HeaderLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HeaderLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.header_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HeaderLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.icon_cart;
      ImageView iconCart = ViewBindings.findChildViewById(rootView, id);
      if (iconCart == null) {
        break missingId;
      }

      id = R.id.icon_menu;
      ImageView iconMenu = ViewBindings.findChildViewById(rootView, id);
      if (iconMenu == null) {
        break missingId;
      }

      id = R.id.icon_search;
      ImageView iconSearch = ViewBindings.findChildViewById(rootView, id);
      if (iconSearch == null) {
        break missingId;
      }

      id = R.id.search_card;
      CardView searchCard = ViewBindings.findChildViewById(rootView, id);
      if (searchCard == null) {
        break missingId;
      }

      id = R.id.search_edit_text;
      EditText searchEditText = ViewBindings.findChildViewById(rootView, id);
      if (searchEditText == null) {
        break missingId;
      }

      return new HeaderLayoutBinding((ConstraintLayout) rootView, iconCart, iconMenu, iconSearch,
          searchCard, searchEditText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
