package com.example.foodie.ui.orderHistory;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodie.databinding.FragmentOrderListBinding;
import com.example.foodie.models.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderListFragment extends Fragment implements IOrderListView {

    private FragmentOrderListBinding binding;
    private MyOrderPresenter presenter;
    private OrderListAdapter adapter;
    private Context context;
    public OrderListFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MyOrderPresenter(this,context);
        adapter = new OrderListAdapter(getActivity(),new ArrayList<>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void showOrderOnGoing(List<Order> orderOnGoings) {
       requireActivity().runOnUiThread(()->{
           adapter.updateData(orderOnGoings);
       });
    }

    @Override
    public void showOrderHistory(List<Order> orderHistories) {
        requireActivity().runOnUiThread(()->{
            adapter.updateData(orderHistories);
        });
    }

    @Override
    public void showLoading() {
        if(isAdded()){
            requireActivity().runOnUiThread(()->{
                binding.progressBar.setVisibility(View.VISIBLE);
            });
        }
    }

    @Override
    public void hideLoading() {
      if(isAdded()){
          requireActivity().runOnUiThread(()->{
              binding.progressBar.setVisibility(View.GONE);
          });
      }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showOrderEmpty() {
        if(isAdded()){
            requireActivity().runOnUiThread(()->{
                binding.empty.setVisibility(View.VISIBLE);
            });
        }
    }

    public void getOnGoingOrders(){
        if(presenter == null){
            presenter = new MyOrderPresenter(this,context);
        }
        presenter.loadOrderOngoing();
    }

    public void getHistoryOrders(){
        if(presenter == null){
            presenter = new MyOrderPresenter(this,context);
        }
        presenter.loadOrderHistory();
    }
}