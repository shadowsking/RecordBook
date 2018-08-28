package com.example.king.recordbook.mvp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

import com.example.king.recordbook.api.NetworkManager;
import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.db.DbHelper;
import com.example.king.recordbook.mvp.base.BasePresenter;
import com.example.king.recordbook.mvp.view.group.GroupView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KING on 04.02.2018.
 */

public class GroupPresenter extends BasePresenter<GroupView> {

///    private DbHelper dbHelper;

    public GroupPresenter(){
        //dbHelper = new DbHelper(getContext());
        //groupFragment.setAdapter(getList());
    }

    public ArrayList<GroupModel> getList(){
        return getDbHelper().getGroupTable().getList();
    }

    public void addGroup(int id_group, String name, int admission_year){ //добавляет в локальную бд
        getDbHelper().getGroupTable().insert(id_group, name, admission_year);
    }

    public void getGroup(){
        //getDbHelper().getGroupTable().delete(1); хз зачем добавил, надо разобраться и удалить
        if(getmNetworkManager().checkNetworkConnection(getContext())) {
            getmNetworkManager().getmApiService().getGrouprx(getmNetworkManager().getToken(getContext()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> getView().addArray(response), this::throwableEcho);
        } else {
            getView().addArray(getList());
            if(getList()!=null) Log.d("GROUPList", "SIZE: " + getList().size());
            else Log.d("GROUPList", "SIZE: NULL");
        }
        //getPoint();
    }

    public void addGroup(String name, String year){
        Map<String, RequestBody> map = new HashMap<>();
        map.put("token", NetworkManager.toRequestBody(getmNetworkManager().getToken(getContext())));
        map.put("name", NetworkManager.toRequestBody(name));
        map.put("year", NetworkManager.toRequestBody(year));

        if(getmNetworkManager().checkNetworkConnection(getContext())) {
            getmNetworkManager().getmApiService().addNewGroup(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::throwableEcho, this::throwableEcho);
        }
    }
}
