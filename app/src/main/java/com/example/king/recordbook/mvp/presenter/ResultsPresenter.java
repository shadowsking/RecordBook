package com.example.king.recordbook.mvp.presenter;

import android.util.Log;

import com.example.king.recordbook.mvp.base.BasePresenter;
import com.example.king.recordbook.mvp.view.point.PointView;
import com.example.king.recordbook.mvp.view.result.ResultView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KING on 09.06.2018.
 */

public class ResultsPresenter extends BasePresenter<ResultView> {

    public ResultsPresenter() {
    }

    public void getResults(int id_student, int semester){
            if(getmNetworkManager().checkNetworkConnection(getContext())) {
                getmNetworkManager().getmApiService().getStudentResults(getmNetworkManager().getToken(getContext()), id_student, semester)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> getView().setAdapter(response), this::throwableEcho);
            }
    }

    public void getStudentPResults(int id_student, int id_point){
        if(getmNetworkManager().checkNetworkConnection(getContext())) {
            getmNetworkManager().getmApiService().getStudentPointResults(getmNetworkManager().getToken(getContext()), id_student, id_point)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> getView().initGraph(response), this::throwableEcho);
        }
    }

}
