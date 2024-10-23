package com.example.foodie.ui.profile;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivityEditProfileBinding;
import com.example.foodie.models.User;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener,IEditProfileView {

    private ActivityEditProfileBinding binding;
    private EditProfilePresent present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        present = new EditProfilePresent(this,this);
        present.getProfile();
        binding.backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.backButton.getId()){
            finish();
        }
    }

    @Override
    public void showProfile(User user) {
        binding.userEmail.setText(user.getEmail());
        binding.userFullname.setText(user.getFirstName() + " " + user.getLastName());
        binding.userAddress.setText(user.getAddress());
        binding.phoneNumber.setText(user.getPhoneNumber());
        binding.email.setText(user.getEmail());
    }
}