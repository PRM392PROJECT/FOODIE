package com.example.foodie.ui.orderHistory;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodie.customview.CollectionPagerAdapter;
import com.example.foodie.databinding.FragmentMyOrderBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFragment extends Fragment {
    private FragmentMyOrderBinding binding;
    private List<Fragment> fragments;
    private CollectionPagerAdapter adapter;
    private Context context;
    private boolean isLoadedData = false;

    public MyOrderFragment(Context context) {
          this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragments();
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new OrderListFragment(context));
        fragments.add(new OrderListFragment(context));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragments();
        adapter = new CollectionPagerAdapter(requireActivity(), fragments);
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("On Going");
                        break;
                    case 1:
                        tab.setText("History");
                        break;
                }
            }
        }).attach();
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                  OrderListFragment fragment = (OrderListFragment)  adapter.createFragment(0);
                  fragment.getOnGoingOrders();
                }else{
                    OrderListFragment fragment = (OrderListFragment)  adapter.createFragment(1);
                    fragment.getHistoryOrders();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    OrderListFragment fragment = (OrderListFragment)  adapter.createFragment(0);
                    fragment.getOnGoingOrders();
                }else{
                    OrderListFragment fragment = (OrderListFragment)  adapter.createFragment(1);
                    fragment.getHistoryOrders();
                }
            }
        });

    }

    public void reset() {
        binding.tabLayout.post(()->{
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));
        });
    }

    public void loadData() {
       if(!isLoadedData){
           binding.tabLayout.post(()->{
               binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));
               isLoadedData = true;
           });
       }
    }
}
