package com.example.king.recordbook.mvp.view.group;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.mvp.CurrentDate;
import com.example.king.recordbook.mvp.presenter.GroupPresenter;
import com.example.king.recordbook.mvp.view.student.StudentFragment;
import com.example.king.recordbook.navigation.NavigationBuilder;
import com.example.king.recordbook.navigation.NavigationFragment;
import com.example.king.recordbook.navigation.menu.MenuActions;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.king.recordbook.navigation.AutoLayoutNavigationBuilder.navigation;

/**
 * Created by KING on 04.02.2018.
 */

public class GroupFragment extends NavigationFragment implements GroupView{
    @BindView(R.id.groupRView)
    RecyclerView recyclerView;
    CurrentDate date;
    int semester;

    private GroupPresenter mGroupPresenter;
    public GroupRVAdapter adapter;

    public GroupFragment() {
    }

    public static Fragment newInstance(int id_point) {
        Bundle args = new Bundle();
        Fragment fragment = new GroupFragment();
        args.putInt("id_point", id_point);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavigationBuilder buildNavigation() {
        return navigation(R.layout.fragment_group)
                .includeToolbar()
                .toolbarTitle("Группы")
                .toolbarNavigationIcon(-1);
                //.menuRes(R.menu.test_menu_1, buildGlobalActions());
    }


    private static MenuActions buildGlobalActions() {
        return new MenuActions.Builder()
                .action(R.id.back,() -> {
                })
                .build();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container,savedInstanceState);
        ButterKnife.bind(this, view);
        date = new CurrentDate(getContext());


        mGroupPresenter = new GroupPresenter();
        mGroupPresenter.attachView(this);
        mGroupPresenter.attachContext(this.getContext());

        adapter = new GroupRVAdapter(mGroupPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mGroupPresenter.getGroup();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void addArray(ArrayList<GroupModel> data) {
        adapter.addData(data);
        Log.d("SIZE", String.valueOf(data.size()));
    }

    @Override
    public void showStudentFragment(int id_group, int admission_year) {

        int course = Integer.valueOf(date.getCurrentDate()[2]) - admission_year;
        int m = Integer.valueOf(date.getCurrentDate()[1]);
        //дата по серверу

/*
        int course = Calendar.YEAR - admission_year;
        int m = Calendar.MONTH;*/
        int semester;
        if(m<8){
            semester = course*2;
        } else semester = course*2 + 1;
        Log.d("SEMESTER", "showStudentFragment: " + Calendar.YEAR); //todo не работает тут

        Fragment fragment = StudentFragment.newInstance(id_group, semester, getArguments().getInt("id_point"));

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }

    @Override
    @OnClick(R.id.addNewGroup)
    public void addNewGroup() {
        final View viewDialog = getLayoutInflater().inflate(R.layout.group_insert_dialog, null);
        final EditText nameGroup = ButterKnife.findById(viewDialog, R.id.nameGroup);
        final Spinner spinner = ButterKnife.findById(viewDialog ,R.id.chooseYear);

        String years[] = new String[11];
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        int start_year = current_year - 10;
        for(int i=0; i<11; ++i){
            years[i] = String.valueOf(start_year);
            start_year++;
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, years);
        spinner.setAdapter(adapter);

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Добавить группу")
                .setView(viewDialog)
                .setPositiveButton("Добавить", (dialogInterface, i) -> {
                    String name = String.valueOf(nameGroup.getText());

                    mGroupPresenter.addGroup(name,spinner.getSelectedItem().toString());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mGroupPresenter.getGroup();
                })
                .setNegativeButton("Отменить", null)
                .create();
        dialog.show();
    }

}
