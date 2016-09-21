package com.example.dllo.baidumusic.VolleyRequest;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/9/20.
 */
public class MyApp extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static Context getContext(){
        return context;
    }
}
