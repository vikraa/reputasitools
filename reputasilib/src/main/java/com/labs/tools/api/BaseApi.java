package com.labs.tools.api;

import android.os.AsyncTask;

import com.labs.tools.callback.Callback;

/**
 * Created by vikraa on 12/5/2015.
 */
public abstract class BaseApi<T, E extends Callback> {

    protected void getDataAsync(E callback) {
       Runnable runnable = new Runnable() {
           @Override
           public void run() {

           }
       };
        new Thread(runnable).start();
    }
}
