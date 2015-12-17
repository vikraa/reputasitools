package com.labs.tools.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/17/2015.
 */
public abstract class BaseCloudResponse<T> {
    @SerializedName("result")
    private T mResult;

    public T getResult() {
        return mResult;
    }
}
