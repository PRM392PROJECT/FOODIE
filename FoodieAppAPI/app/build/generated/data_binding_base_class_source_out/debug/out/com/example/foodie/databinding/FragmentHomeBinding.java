// Generated by view binder compiler. Do not edit!
package com.example.foodie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.example.foodie.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final HeaderLayoutBinding header;

  @NonNull
  public final TabLayout tabFoodCategory;

  @NonNull
  public final ViewPager2 viewPagerFood;

  private FragmentHomeBinding(@NonNull RelativeLayout rootView, @NonNull HeaderLayoutBinding header,
      @NonNull TabLayout tabFoodCategory, @NonNull ViewPager2 viewPagerFood) {
    this.rootView = rootView;
    this.header = header;
    this.tabFoodCategory = tabFoodCategory;
    this.viewPagerFood = viewPagerFood;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.header;
      View header = ViewBindings.findChildViewById(rootView, id);
      if (header == null) {
        break missingId;
      }
      HeaderLayoutBinding binding_header = HeaderLayoutBinding.bind(header);

      id = R.id.tabFoodCategory;
      TabLayout tabFoodCategory = ViewBindings.findChildViewById(rootView, id);
      if (tabFoodCategory == null) {
        break missingId;
      }

      id = R.id.viewPagerFood;
      ViewPager2 viewPagerFood = ViewBindings.findChildViewById(rootView, id);
      if (viewPagerFood == null) {
        break missingId;
      }

      return new FragmentHomeBinding((RelativeLayout) rootView, binding_header, tabFoodCategory,
          viewPagerFood);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}