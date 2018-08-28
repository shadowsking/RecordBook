package com.example.king.recordbook.mvp.view.point;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.mvp.presenter.PointPresenter;
import com.example.king.recordbook.mvp.view.group.GroupFragment;
import com.example.king.recordbook.mvp.view.student.StudentFragment;
import com.example.king.recordbook.navigation.NavigationBuilder;
import com.example.king.recordbook.navigation.NavigationFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.king.recordbook.navigation.AutoLayoutNavigationBuilder.navigation;

/**
 * Created by KING on 08.06.2018.
 */

public class PointFragment extends NavigationFragment implements PointView{
    @BindView(R.id.groupRView)
    RecyclerView recyclerView;

    private PointPresenter mPointPresenter;
    public PointRVAdapter adapter;

    public PointFragment() {
    }

    public static Fragment newInstance(int id_event, ArrayList<PointModel.PointBean> events) {
        Bundle args = new Bundle();
        Fragment fragment = new PointFragment();
        args.putInt("id_event", id_event);
        args.putSerializable("events", events);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavigationBuilder buildNavigation() {
        return navigation(R.layout.fragment_group)
                .includeToolbar()
                .toolbarTitle("Нормативы")
                .toolbarNavigationIcon(-1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container,savedInstanceState);
        ButterKnife.bind(this, view);

        mPointPresenter = new PointPresenter();
        mPointPresenter.attachView(this);
        mPointPresenter.attachContext(this.getContext());

        ArrayList<PointModel.PointBean> events = (ArrayList<PointModel.PointBean>) getArguments().getSerializable("events");


        adapter = new PointRVAdapter(mPointPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if(events!=null) mPointPresenter.getPoint(events);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setAdapter(ArrayList<PointModel.PointBean> data) {
        adapter.addData(data, getArguments().getInt("id_event"));
        Log.d("SIZE", String.valueOf(data.size()));
    }

    @Override
    public void showGroupFragment(int id_point) {
        Fragment fragment = GroupFragment.newInstance(id_point);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }

}
