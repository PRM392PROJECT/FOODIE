package com.example.foodie.ui.authen;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodie.R;
import com.example.foodie.customview.CollectionPagerAdapter;
import com.example.foodie.databinding.ActivityAuthenBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class AuthenActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAuthenBinding binding;
    private List<Fragment> fragmentList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAuthenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up back button listener
        binding.backButton.setOnClickListener(this);

        // Set up ViewPager2 with TabLayout
        setUpViewPagerWithTabs();
    }

    private void setUpViewPagerWithTabs() {
        // Initialize ViewPager2 with the adapter
        fragmentList = new ArrayList(){
            {
                add(new LoginFragment());
                add(new RegisterFragment());
            }
        };
        CollectionPagerAdapter adapter = new CollectionPagerAdapter(this,fragmentList);
        binding.viewPager.setAdapter(adapter);

        // Connect TabLayout with ViewPager2 using TabLayoutMediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Login");
                        break;
                    case 1:
                        tab.setText("Sign Up");
                        break;
                }
            }
        }).attach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.backButton.getId()) {
            finish();
        }
    }

}
