package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.callback.Callback;

/**
 * Created by vikraa on 12/13/2015.
 */
public class ContributeApi extends BaseApi<Void, Callback<Void>> {

    private Context mContext;

    public ContributeApi(Context context) {
        this.mContext = context;
    }

    @Override
    public void getDataAsync(Callback<Void> callback) {

    }

    public void addContribute(String name, String number, String notes, Callback<Void> callback) {

    }
}
