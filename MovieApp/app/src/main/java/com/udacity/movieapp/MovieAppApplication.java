package com.udacity.movieapp;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by ahmed agamy on 06/05/2017.
 */

public class MovieAppApplication extends Application{

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public static Context getContext() {
        return appContext;
    }

}
