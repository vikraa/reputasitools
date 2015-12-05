package com.labs.tools;

import android.app.Application;
import android.content.Context;

import retrofit.RestAdapter;

/**
 * Created by Vikraa on 11/20/2015.
 */
public abstract class MyApplication extends Application {


    private static Context mContext;
    private static boolean mRetrofitLogEnabled;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    public void setRetrofitLogEnabled(boolean enabled) {
        mRetrofitLogEnabled = enabled;
    }

    public abstract String getApiEndPoint();

    public abstract String getApiClientId();

    public abstract String getDefaultTimestampFormat();

    public static RestAdapter.LogLevel getLogLevel() {
        return mRetrofitLogEnabled ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE;
    }

}
