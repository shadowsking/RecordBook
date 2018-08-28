package com.example.king.recordbook.mvp.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.king.recordbook.api.NetworkManager;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.db.DbHelper;
import com.example.king.recordbook.mvp.base.BasePresenter;
import com.example.king.recordbook.mvp.view.group.GroupView;
import com.example.king.recordbook.mvp.view.point.PointView;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KING on 08.06.2018.
 */

public class PointPresenter extends BasePresenter<PointView> {
    private DbHelper dbHelper;

    public PointPresenter(){
        dbHelper = new DbHelper(getContext());
    }


    public void getPoint(@NonNull ArrayList<PointModel.PointBean> events){
        getView().setAdapter(events);
    }
}
