package com.example.king.recordbook.mvp.presenter;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.king.recordbook.api.NetworkManager;
import com.example.king.recordbook.api.model.Token;
import com.example.king.recordbook.mvp.base.BasePresenter;
import com.example.king.recordbook.mvp.view.auth.AuthView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KING on 02.02.2018.
 */

public class AuthPresenter extends BasePresenter<AuthView>{

    public AuthPresenter(){

    }

    public void auth(String login, String password){
        Map<String, RequestBody> map = new HashMap<>();
        map.put("login", NetworkManager.toRequestBody(login));
        map.put("pass", NetworkManager.toRequestBody(password));

        getmNetworkManager().getmApiService().getToken(map).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                getmNetworkManager().setToken(response.body(), getContext());
                getView().closeFragment();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e("Response", t.getMessage());
            }
        });
    }
}
