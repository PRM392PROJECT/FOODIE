package com.example.foodie;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodie.ui.favorite.FavoriteFragment;
import com.example.foodie.ui.orderHistory.MyOrderFragment;
import com.example.foodie.ui.home.HomeFragment;
import com.example.foodie.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodie.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeFragment home;
    private FavoriteFragment favorite;
    private ProfileFragment profile ;
    private MyOrderFragment history ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ẩn ActionBar nếu nó có
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        home = new HomeFragment();
        favorite = new FavoriteFragment();
        profile = new ProfileFragment();
        history = new MyOrderFragment(this);
        setNavBottom();
    }

    private void setNavBottom() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Thêm các fragment vào fragment manager để quản lý
        transaction.add(R.id.content, home, "Home");
        transaction.add(R.id.content, favorite, "Favorite").hide(favorite);
        transaction.add(R.id.content, profile, "Profile").hide(profile);
        transaction.add(R.id.content, history, "History").hide(history);
        transaction.commit();
        binding.navView.post(() -> {
            binding.navView.setSelectedItemId(R.id.navigation_home);
        });
        // Tự động chọn home khi mở ứng dụng
        binding.navView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_home){
                    showFragment(home);
                    home.loadData();
                    return true;
                }else if(item.getItemId() == R.id.navigation_favorite){
                    showFragment(favorite);
                    favorite.loadData();
                    return true;
                }
                else if(item.getItemId() == R.id.navigation_history){
                    showFragment(history);
                    history.loadData();
                    return true;
                }
                else if(item.getItemId() == R.id.navigation_profile){
                    showFragment(profile);
                    profile.loadData();
                    return true;
                }
                return false;
            }
        });
        binding.navView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.navigation_home){
                    home.reset();
                }
                else if(item.getItemId() == R.id.navigation_favorite){
                    favorite.reset();
                }
                else if(item.getItemId() == R.id.navigation_history){
                    history.reset();
                }
                else if(item.getItemId() == R.id.navigation_profile){
                    profile.reset();
                }
            }
        });
    }

    // Hiện fragment khi nhấn vào mục menu
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        List<Fragment> fragments = Arrays.asList(home, favorite, profile, history);
        // Ẩn các fragment
        for (Fragment fra : fragments) {
            if (fra != fragment) {
                transaction.hide(fra);
            }
        }
        // Hiện fragment
        transaction.show(fragment);
        transaction.commit();
    }

}
