package com.example.king.recordbook.mvp.view.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.mvp.presenter.EventPresenter;
import com.example.king.recordbook.mvp.view.point.PointFragment;
import com.example.king.recordbook.mvp.view.student.StudentFragment;
import com.example.king.recordbook.navigation.NavigationBuilder;
import com.example.king.recordbook.navigation.NavigationFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.king.recordbook.navigation.AutoLayoutNavigationBuilder.navigation;


/**
 * Created by KING on 03.06.2018.
 */

public class EventFragment extends NavigationFragment implements EventView {
    @BindView(R.id.eventRView)
    RecyclerView recyclerView;

    private EventPresenter mEventPresenter;
    public EventRVAdapter adapter;

    private ArrayList<PointModel> events;

    public EventFragment() {
    }

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        Fragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance(int id_event) {
        Bundle args = new Bundle();
        Fragment fragment = new EventFragment();
        args.putInt("id_event", id_event);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavigationBuilder buildNavigation() {
        return navigation(R.layout.fragment_event)
                .includeToolbar()
                .toolbarTitle("Мероприятия")
                .toolbarNavigationIcon(-1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_event, container, false);
        View view = super.onCreateView(inflater, container,savedInstanceState);
        ButterKnife.bind(this, view);

        mEventPresenter = new EventPresenter();
        mEventPresenter.attachView(this);
        mEventPresenter.attachContext(this.getContext());


        adapter = new EventRVAdapter(mEventPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mEventPresenter.getEvent();
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void setAdapter(ArrayList<PointModel> data) {
        adapter.addData(data);
        events = data;
    }

    @Override
    public void showPointFragment(int id_event) {
        Fragment fragment = PointFragment.newInstance(id_event, events.get(id_event).getPoint());
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }

}
