package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.callback.Callback;
import com.labs.tools.net.RetrofitHelper;

/**
 * Created by vikraa on 12/13/2015.
 */
public class SmsApi extends BaseApi<Void, Callback<Void>> {
    private Context mContext;
    private RetrofitHelper mRetrofit;
    private static SmsApi sInstance;

    public static SmsApi getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SmsApi(context);
        }
        return sInstance;
    }

    private SmsApi(Context context) {
        this.mContext = context;
        mRetrofit = new RetrofitHelper();
    }

    public void readInbox(Callback<Void> callback) {

    }

    public void readOutbox(Callback<Void> callback) {

    }

    public void readSent(Callback<Void> callback) {

    }

    public void readDraft(Callback<Void> callback) {

    }

    public void addBlockedSender(String name) {

    }

    public void removeBlockedSender(String name) {

    }



}
