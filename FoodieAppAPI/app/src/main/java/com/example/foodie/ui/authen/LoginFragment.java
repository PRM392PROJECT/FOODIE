package com.example.foodie.ui.authen;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodie.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements ILoginView , View.OnClickListener {

    private FragmentLoginBinding binding;
    private LoginPresenter presenter;
    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this,getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        binding.progressBar2.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar2.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getActivity(), "Login successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        getActivity().setResult(RESULT_OK, intent); // Trả về kết quả thành công
        getActivity().finish();
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnLogin.getId()){
            String email = binding.edittextEmail.getText().toString();
            String password = binding.edittextPassword.getText().toString();
            presenter.login(email,password);
        }
    }
}