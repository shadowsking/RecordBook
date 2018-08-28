package com.example.king.recordbook.mvp;

import android.content.Context;
import android.util.Log;

import com.example.king.recordbook.api.NetworkManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KING on 12.06.2018.
 */

public class CurrentDate{
    private NetworkManager mNetworkManager;
    private Context context;
    private String[] currentDate;

    public CurrentDate(Context context){
        mNetworkManager = new NetworkManager(context);
        this.context = context;
        getDate();
    }

    private void getDate(){
        if(mNetworkManager.checkNetworkConnection(context)) {
            mNetworkManager.getmApiService().getCurrentDate()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(r->{currentDate = r.split("-");}, o->{});
        }
    }

    public String[] getCurrentDate() {
        return currentDate;
    }
}
