package com.example.foodie.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodie.databinding.FragmentProfileBinding;
import com.example.foodie.models.User;
import com.example.foodie.ui.authen.AuthenActivity;

public class ProfileFragment extends Fragment implements IProfileView ,View.OnClickListener{

    private FragmentProfileBinding binding;
    private ProfilePresenter presenter;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProfilePresenter(this,getActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.nextLogout.setOnClickListener(this);
        presenter.loadProfile();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void checkLogin() {

    }

    @Override
    public void loadProfile(User user) {
        binding.email.setText(user.getEmail());
        binding.fullName.setText(String.format("%s %s",user.getFirstName(),user.getLastName()));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.nextLogout.getId()){
            presenter.logOut();
            Intent intent = new Intent(requireContext(), AuthenActivity.class);
            startActivityForResult(intent, 100);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == getActivity().RESULT_OK) {
            presenter.loadProfile(); // Gọi lại hàm loadProfile
        }
    }

    public void reset() {
        presenter.loadProfile();
    }
}