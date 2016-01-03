package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.callback.Callback;
import com.labs.tools.callback.CallerCallback;

/**
 * Created by vikraa on 12/13/2015.
 */
public class CallerApi extends BaseApi<Void, Callback<Void>> {
    private Context mContext;
    private CallerCallback mListener;
    private static CallerApi sInstance;

    public static CallerApi getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CallerApi(context);
        }
        return sInstance;
    }

    private CallerApi(Context context) {
        this.mContext = context;
    }

    public void setListener(CallerCallback listener) {
        this.mListener = listener;
    }

    public CallerCallback getListener() {
        return this.mListener;
    }


}
