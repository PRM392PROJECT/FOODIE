package com.example.foodie.ui.authen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.R;
import com.example.foodie.databinding.FragmentRegisterBinding;
import com.example.foodie.models.UserRegister;

public class RegisterFragment extends Fragment implements IRegisterView, View.OnClickListener {

    private FragmentRegisterBinding binding;
    private RegisterPresenter registerPresenter;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerPresenter = new RegisterPresenter(getContext(), this);
        binding.btnRegister.setOnClickListener(this);
    }

    @Override
    public void registerFalse(String mess) {
        // Hiển thị thông báo lỗi được cung cấp
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(getContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public UserRegister getUserRegister() {
        // Lấy dữ liệu từ giao diện
        String firstName = binding.firstNameEdit.getText().toString().trim();
        String lastName = binding.lastNameEdit.getText().toString().trim();
        String phoneNumber = binding.moblileEdt.getText().toString().trim();
        String email = binding.emailEdt.getText().toString().trim();
        String password = binding.passWordEdt.getText().toString().trim();

        // Kiểm tra các trường không được để trống
        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return null;
        }

        // Tạo đối tượng UserRegister
        String address = ""; // Bổ sung logic nếu cần
        int roleId = 1; // Đặt role ID mặc định, có thể thay đổi nếu cần
        return new UserRegister(firstName, lastName, phoneNumber, email, password, address, roleId);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnRegister.getId()) {
            String password = binding.passWordEdt.getText().toString().trim();
            String confirmPass = binding.repassWordEdt.getText().toString().trim();

            // Kiểm tra mật khẩu có khớp không
            if (!password.equals(confirmPass)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                registerPresenter.register();
            }
        }
    }
}
