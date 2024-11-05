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
import com.example.foodie.untils.UserInfoManager;

public class ProfileFragment extends Fragment implements IProfileView ,View.OnClickListener{

    private FragmentProfileBinding binding;
    private ProfilePresenter presenter;
    private boolean isLoadedData = false;
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
        binding.nextInfor.setOnClickListener(this);
        binding.nextAddress.setOnClickListener(this);
        binding.nextLogin.setOnClickListener(this);
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
        if(user !=null){
            binding.email.setText(user.getEmail());
            binding.fullName.setText(String.format("%s %s",user.getFirstName(),user.getLastName()));
            binding.nextLogin.setVisibility(View.GONE);
            binding.nextLogout.setVisibility(View.VISIBLE);
        }
        else{
            binding.email.setText("-----------");
            binding.fullName.setText("-----------");
            binding.nextLogin.setVisibility(View.VISIBLE);
            binding.nextLogout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.nextLogin.getId()){
            Intent intent = new Intent(requireContext(), AuthenActivity.class);
            startActivityForResult(intent, 100);
        }else if(v.getId() == binding.nextLogout.getId()){
            presenter.logOut();
        }
        else if(v.getId() == binding.nextInfor.getId()){
            Intent intent = new Intent(requireContext(),EditProfileActivity.class);
            startActivity(intent);
        }
        else if(v.getId()== binding.nextAddress.getId()){
            Intent intent = new Intent(requireContext(),EditAddressActivity.class);
            startActivity(intent);
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

    public void loadData() {
        if(!isLoadedData){
            presenter.loadProfile();
        }
    }
}