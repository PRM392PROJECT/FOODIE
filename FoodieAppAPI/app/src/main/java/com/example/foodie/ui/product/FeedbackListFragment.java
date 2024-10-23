package com.example.foodie.ui.product;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodie.databinding.FragmentFeedbackListBinding;

public class FeedbackListFragment extends Fragment {

    private FragmentFeedbackListBinding binding;

    public FeedbackListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeedbackListBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }
}