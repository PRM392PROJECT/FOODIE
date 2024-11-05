package com.example.foodie.ui.profile;

import android.content.Context;
import android.util.Log;

import com.example.foodie.models.User;
import com.example.foodie.models.UserUpdateRequest;
import com.example.foodie.service.ApiClient;
import com.example.foodie.service.ApiService;
import com.example.foodie.untils.UserInfoManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddressPresent {
    private IEditAddressView view;
    private Context context;
    private ApiService apiService;
    public EditAddressPresent( Context context,IEditAddressView view) {
        this.view = view;
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }
    public void saveLocation(String address) {
        User user = UserInfoManager.getUserInfo(context);
        user.setAddress(address);
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(user);
        Call<User> call = apiService.updateUser(userUpdateRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful() && response.body()!=null){
                    UserInfoManager.saveUserInfo(response.body(),context);
                    Log.i("TAG", "update success:");
                    view.updateSuccess();
                }
                else {
                    Log.e("TAG", "update fail:"+response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "update fail:"+t.getMessage());
            }
        });
    }
}
