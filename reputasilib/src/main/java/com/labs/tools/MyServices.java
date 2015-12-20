package com.labs.tools;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class MyServices extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
