package com.example.foodie.presenters.cart;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.foodie.models.CartItem;
import com.example.foodie.models.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartPresenter {
    private ICartView view;
    public CartPresenter(ICartView view){
        this.view = view;
    }

    public void delete(){
        view.delete();
    }

    @SuppressLint("StaticFieldLeak")
    public AsyncTask getCart(){
        return new AsyncTask<Void,Void,List<CartItem>>() {
            @Override
            protected void onPreExecute() {

            }
            @Override
            protected List<CartItem> doInBackground(Void... voids) {
                List<CartItem> carts = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    CartItem cart = new CartItem();
                    carts.add(cart);
                }
                return carts;
            }
            @Override
            protected void onPostExecute(List<CartItem> cartItems) {
                view.showCart(cartItems);
            }
        }.execute();

    }

}
