package com.example.king.recordbook.mvp.view.student;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Student.StudentModel;
import com.example.king.recordbook.mvp.presenter.StudentPresenter;
import com.example.king.recordbook.mvp.view.result.ResultFragment;
import com.example.king.recordbook.navigation.NavigationBuilder;
import com.example.king.recordbook.navigation.NavigationFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.king.recordbook.navigation.AutoLayoutNavigationBuilder.navigation;

/**
 * Created by KING on 07.02.2018.
 */

public class StudentFragment extends NavigationFragment implements StudentView{
    @BindView(R.id.studentRView)
    RecyclerView recyclerView;
    @BindView(R.id.addNewStudent)
    FloatingActionButton btnAddNewStudent;
    @BindView(R.id.semester)
    Spinner semesters;

    private StudentPresenter mStudentPresenter;
    public StudentRVAdapter adapter;

    public StudentFragment() {

    }

    public static Fragment newInstance(int id_group, int semester, int id_point) {
        Bundle args = new Bundle();
        Fragment fragment = new StudentFragment();
        args.putInt("id_group", id_group);
        args.putInt("semester", semester);
        Log.d("ID_POINT", "newInstance: " + id_point);
        args.putInt("id_point", id_point);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavigationBuilder buildNavigation() {
        return navigation(R.layout.fragment_student)
                .includeToolbar()
                .toolbarTitle("Студенты");
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_student, container, false);
        View view = super.onCreateView(inflater, container,savedInstanceState);
        ButterKnife.bind(this, view);

        int id_point = getArguments().getInt("id_point");
        int id_group = getArguments().getInt("id_group");

        initSemesters();

        mStudentPresenter = new StudentPresenter(id_group, id_point);
        mStudentPresenter.attachView(this);
        mStudentPresenter.attachContext(this.getContext());

        adapter = new StudentRVAdapter(mStudentPresenter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

//       mStudentPresenter.getStudents(this.getArguments().getInt("id_group"));

        //int semester = Integer.parseInt(semesters.getSelectedItem().toString());
        //mStudentPresenter.getGroupResult(semester);

        semesters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int semester = Integer.parseInt(semesters.getSelectedItem().toString());
                mStudentPresenter.getGroupResult(semester);
                mStudentPresenter.setSemester(semester);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        recyclerView.setAdapter(adapter);

        btnAddNewStudent.setOnClickListener(v -> addStudent());

        return view;
    }



    private void initSemesters(){
        int current_semester = getArguments().getInt("semester");
        String d[] = new String[current_semester];
        for(int i = 0; i<current_semester; i++){
            d[i]= String.valueOf(i+1);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, d);
        semesters.setAdapter(adapter);
        semesters.setSelection(current_semester-1);
    }

    @Override
    public void showResultsFragment(int id_student, int semester) {
        Fragment fragment = ResultFragment.newInstance(id_student, semester);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setAdapter(ArrayList<StudentModel> data) {
        adapter.addData(data);
    }

    private void addStudent(){
        final View viewDialog = getLayoutInflater().inflate(R.layout.student_insert_dialog, null);
        final EditText name = ButterKnife.findById(viewDialog, R.id.studentName);
        final Spinner spinner = ButterKnife.findById(viewDialog ,R.id.chooseSex);

        String sex[] = new String[2];
        sex[0] = "Муж";
        sex[1] = "Жен";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, sex);
        spinner.setAdapter(adapter);
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Добавить нового студента")
                .setView(viewDialog)
                .setPositiveButton("Добаить", (dialog1, which) -> {
                    mStudentPresenter.addNewStudent(getArguments().getInt("id_group"), name.getText().toString(), spinner.getSelectedItem().toString());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mStudentPresenter.getStudents(getArguments().getInt("id_group"));
                })
                .setNegativeButton("Отменить", null)
                .create();
        dialog.show();
    }

}
