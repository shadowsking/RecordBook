package com.example.king.recordbook.mvp.presenter;

import android.content.Context;

import com.example.king.recordbook.api.NetworkManager;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.mvp.base.BasePresenter;
import com.example.king.recordbook.mvp.view.event.EventView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KING on 03.06.2018.
 */

public class EventPresenter extends BasePresenter<EventView>{
    public EventPresenter(){
    }

    public void getEvent(){
        if(getmNetworkManager().checkNetworkConnection(getContext())) {
            getmNetworkManager().getmApiService().getPointRx()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> getView().setAdapter(response), this::throwableEcho);
        }
    }
}
