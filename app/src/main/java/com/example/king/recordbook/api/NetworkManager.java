package com.example.king.recordbook.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.king.recordbook.api.model.Token;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KING on 02.02.2018.
 */

public class NetworkManager {
    private ApiService mApiService;
    private SharedPreferences mSharedPreferences;

    public NetworkManager(Context context) {
        if(checkNetworkConnection(context))
            init();
    }

    public boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }

    public void init(){
        OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl originalUrl = chain.request().url();
                HttpUrl url = originalUrl.newBuilder()
                        .build();
                Request.Builder reBuilder = chain.request().newBuilder().url(url);
                Request request = reBuilder.build();
                Log.d("URL", url.toString());
                return chain.proceed(request);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://192.168.1.25/index.php/")
                //.baseUrl("http://www.rbook/index.php/")
                //.baseUrl("http://192.168.20.1/index.php/") //для виртуальной машины
                .baseUrl("http://192.168.1.50/index.php/") //для телефона
                //.baseUrl("http://192.168.43.144/index.php/") //для телефона
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //add rxjava
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(mOkHttpClient)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public ApiService getmApiService(){
        return mApiService;
    }

    public void setToken(Token token, Context context){
        mSharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        mSharedPreferences.edit().putString("token", token.getToken()).apply();
    }

    public String getToken(Context context){
        mSharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        return mSharedPreferences.getString("token","");
    }

    public void setDate(Token token, Context context){
        mSharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        mSharedPreferences.edit().putString("token", token.getToken()).apply();
    }

    public static RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }
}
