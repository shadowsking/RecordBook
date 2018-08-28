package com.example.king.recordbook.mvp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.NetworkManager;
import com.example.king.recordbook.api.model.Result.ResultModel;
import com.example.king.recordbook.api.model.Student.StudentModel;
import com.example.king.recordbook.mvp.base.BasePresenter;
import com.example.king.recordbook.mvp.view.student.StudentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KING on 07.02.2018.
 */

public class StudentPresenter extends BasePresenter<StudentView> {
    private int id_group;
    private int id_point;
    private ArrayList<ResultModel> results = null;
    private int semester;

    public StudentPresenter(int id_group, int id_point){
        this.id_group = id_group;
        this.id_point = id_point;
    }

    public void getStudents(int id_group) {
        if(getmNetworkManager().checkNetworkConnection(getContext())) {
            getmNetworkManager().getmApiService().getStudentsRx(getmNetworkManager().getToken(getContext()), id_group)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(students -> {
                        if(results!=null){
                            for(int i=0; i<students.size(); ++i){
                                for(int j=0; j<results.size(); ++j){
                                    if(students.get(i).getId_student()==results.get(j).getId_student()){
                                        students.get(i).setResult(results.get(j).getResult());
                                    }
                                }
                            }
                        }
                        getView().setAdapter(students);
                    },this::throwableEcho);
        } else addArr(null);
    }

    private void addArr(Throwable throwable) {
        getView().setAdapter(getDbHelper().getStudentTable().getList(id_group));
    }

    private void saveInDb(ArrayList<StudentModel> stud){
        //getView().setAdapter(stud);
        for(int i=0; i<stud.size(); i++){
            getDbHelper().getStudentTable().insert(id_group, stud.get(i).getName(), stud.get(i).getSex());
        }
        addArr(null);
    }

    public void addNewStudent(int id_group, String name, String sex){
        Map<String, RequestBody> map = new HashMap<>();
        map.put("token", NetworkManager.toRequestBody(getmNetworkManager().getToken(getContext())));
        map.put("id_group", NetworkManager.toRequestBody(String.valueOf(id_group)));
        map.put("name", NetworkManager.toRequestBody(name));
        map.put("sex", NetworkManager.toRequestBody(sex));

        if(getmNetworkManager().checkNetworkConnection(getContext())) {
            getmNetworkManager().getmApiService().addNewStudentRx(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o -> {}, err->{});
            Log.d("Number","1");
        }
    }

    public void getGroupResult(int semester){
        if(id_point!=-1){

            if(getmNetworkManager().checkNetworkConnection(getContext())) {
                getmNetworkManager().getmApiService().getGroupResults(getmNetworkManager().getToken(getContext()), id_group, id_point, semester)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::setResults,throwable -> {getStudents(id_group);});
            }
        } else getStudents(id_group);
    }

    private void setResults(ArrayList<ResultModel> results){
        this.results = results;
        getStudents(id_group);
    }


    public void addResult(int id_student, int semester){
        final EditText resultEdit = new EditText(getContext());
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Добавить результат")
                .setView(resultEdit)
                .setPositiveButton("Добавить", (dialogInterface, i) -> {
                    String result = String.valueOf(resultEdit.getText());

                    Map<String, RequestBody> map = new HashMap<>();
                    map.put("token", NetworkManager.toRequestBody(getmNetworkManager().getToken(getContext())));
                    map.put("id_student", NetworkManager.toRequestBody(String.valueOf(id_student)));
                    map.put("id_point", NetworkManager.toRequestBody(String.valueOf(id_point)));
                    map.put("result", NetworkManager.toRequestBody(String.valueOf(result)));
                    map.put("semester", NetworkManager.toRequestBody(String.valueOf(semester)));

                    if(getmNetworkManager().checkNetworkConnection(getContext())) {
                        getmNetworkManager().getmApiService().newResult(map)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(r->{getGroupResult(semester);}, o->{getGroupResult(semester);});
                    }
                })
                .setNegativeButton("Отменить", null)
                .create();
        dialog.show();
    }



    public void changeResult(){
        Log.d("Изменить", "changeResult: ");
    }

    public int getId_point() {
        return id_point;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
