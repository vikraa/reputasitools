package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.callback.Callback;

/**
 * Created by vikraa on 12/13/2015.
 */
public class ContactApi extends BaseApi<Void, Callback<Void>> {
    private Context mContext;

    public ContactApi(Context context) {
        this.mContext = context;
    }
}
