package com.tester.labs;

import android.app.Activity;
import android.os.Bundle;

import com.labs.tools.MyApplication;

/**
 * Created by vikraa on 12/26/2015.
 */
public class AppSample extends MyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        setRetrofitLogEnabled(true);
    }
}
