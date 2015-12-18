package com.labs.tools.example;

import com.labs.tools.MyApplication;

/**
 * Created by vikraa on 12/18/2015.
 */
public class ExampleApplication extends MyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        setRetrofitLogEnabled(true);
    }

}
