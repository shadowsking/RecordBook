package com.example.king.recordbook.mvp.base;

import android.content.Context;
import android.util.Log;

import com.example.king.recordbook.api.NetworkManager;
import com.example.king.recordbook.db.DbHelper;

/**
 * Created by KING on 02.02.2018.
 */

public abstract class BasePresenter <V extends BaseView>{
    private V mView;
    private Context context;
    private DbHelper dbHelper;
    private NetworkManager mNetworkManager;

    public final void attachView(V view) {
        if (view == null) {
            throw new NullPointerException("View must not be null");
        }
        mView = view;
    }

    public final void detachView() {
        mView = null;
    }

    public final V getView() {
        return mView;
    }

    public final boolean isViewAttached() {
        return mView != null;
    }


    public final void attachContext(Context context){
        this.context = context;
        dbHelper = new DbHelper(context);
        mNetworkManager = new NetworkManager(context);
    }

    public Context getContext() {
        return context;
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void throwableEcho(Object throwable){
        Log.d("Error", String.valueOf(throwable));
    }

    public NetworkManager getmNetworkManager() {
        return mNetworkManager;
    }
}
