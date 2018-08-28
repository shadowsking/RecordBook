package com.example.king.recordbook.mvp.view.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.king.recordbook.R;
import com.example.king.recordbook.mvp.presenter.AuthPresenter;
import com.example.king.recordbook.mvp.view.group.GroupFragment;
import com.example.king.recordbook.mvp.view.student.StudentFragment;
import com.example.king.recordbook.navigation.NavigationBuilder;
import com.example.king.recordbook.navigation.NavigationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.king.recordbook.navigation.AutoLayoutNavigationBuilder.navigation;

/**
 * Created by KING on 02.02.2018.
 */

public class AuthFragment extends NavigationFragment implements AuthView{
    private AuthPresenter mAuthPresenter;

    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.join)
    Button join;


    public static Fragment newInstance() {
        Bundle args = new Bundle();
        Fragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavigationBuilder buildNavigation() {
        return navigation(R.layout.fragment_auth)
                .includeToolbar()
                //.includeBottomNavigation()
                .toolbarTitle("Авторизация");
        //.toolbarSubtitle("Super subtitle test!");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_auth, container, false);
        View view = super.onCreateView(inflater, container,savedInstanceState);
        mAuthPresenter = new AuthPresenter();

        mAuthPresenter.attachView(this);
        mAuthPresenter.attachContext(this.getContext());

        ButterKnife.bind(this, view);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthPresenter.auth(login.getText().toString(), password.getText().toString());
                Log.d("JOIN: ", "Войти");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void closeFragment() {
        //getActivity().getFragmentManager().popBackStack();
        //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        Fragment fragment = GroupFragment.newInstance(-1);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment, fragment.getTag())
                .commit();
    }
}
