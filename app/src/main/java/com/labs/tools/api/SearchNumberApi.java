package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.callback.Callback;

/**
 * Created by vikraa on 12/13/2015.
 */
public class SearchNumberApi extends BaseApi<Void, Callback<Void>> {
    private Context mContext;

    public SearchNumberApi(Context context) {
        this.mContext = context;
    }
}
