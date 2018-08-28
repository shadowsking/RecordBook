package com.example.king.recordbook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.king.recordbook.navigation.NavigationDefaults;

import static com.example.king.recordbook.constants.NavigationIconType.BACK;
import static com.example.king.recordbook.constants.NavigationIconType.CLOSE;
import static com.example.king.recordbook.constants.NavigationIconType.ENTER;
import static com.example.king.recordbook.constants.NavigationItemType.SEARCH;
import static com.example.king.recordbook.navigation.NavigationDefaults.NavigationDefaultsHolder.initDefaults;

/**
 * Created by KING on 04.06.2018.
 */

public class App extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;

        initDefaults(new NavigationDefaults()
                .navigationIcon(BACK, R.drawable.arrow_left)
                //.navigationIcon(SEARCH, R.drawable.magnify)
                //.navigationIcon(BACK, R.drawable.arrow_left)
                //.navigationItem(SEARCH, R.string.menu_back, R.drawable.magnify, R.color.colorPrimary)

                .defaultNavigationIconType(BACK));
                //.defaultBottomNavigationItem(SEARCH)

                //.defaultNavigationIconType(SEARCH)

                /*.navigationIconListener(view -> {
                    Context context = view.getContext();
                    if (context instanceof Activity) {
                        ((Activity) context).onBackPressed();
                    }
                }));*/
    }

    public static Context appContext() {
        return appContext;
    }
}
