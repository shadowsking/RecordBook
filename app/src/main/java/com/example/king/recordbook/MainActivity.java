package com.example.king.recordbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.king.recordbook.api.NetworkManager;
import com.example.king.recordbook.api.model.Token;
import com.example.king.recordbook.mvp.view.auth.AuthFragment;
import com.example.king.recordbook.mvp.view.event.EventFragment;
import com.example.king.recordbook.mvp.view.group.GroupFragment;
import com.example.king.recordbook.navigation.NavigationDefaults;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    NetworkManager networkManager;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_groups:
                        showGroup();
                        return true;
                    case R.id.navigation_events:
                        showPoint();
                        return true;
                }
                return false;
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        NavigationDefaults.NavigationDefaultsHolder.navigationDefaults().navigationIconListener(val->{
            onBackPressed();
        });


        networkManager = new NetworkManager(this);

        if(networkManager.checkNetworkConnection(this)){
            isValidToken();
        } else showGroup();




    }

    public void isValidToken(){
        networkManager.getmApiService().isValidToken(networkManager.getToken(this)).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                show(response.body().isValid());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e("Response", t.getMessage());
            }
        });
    }

    public void show(boolean isValidToken){
        if(!isValidToken){
            showAuth();
        } else {
            showGroup();
        }

    }

    private void showAuth(){
        navigation.setVisibility(View.GONE);
        showFragment(AuthFragment.newInstance());
    }

    private void showGroup(){
        //navigation.setVisibility(View.GONE); //костыль
        navigation.setVisibility(View.VISIBLE);
        showFragment(GroupFragment.newInstance(-1));
    }

    private void showPoint() { showFragment(EventFragment.newInstance());}


    private void showFragment(Fragment fragment){
        if(fragment!=null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment, fragment.getTag());
            //fragmentTransaction.add(R.id.frameLayout, fragment, fragment.getTag());
            fragmentTransaction.commit();
        }
    }
}
