package com.labs.tools.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/20/2015.
 */
public class RestError {
    @SerializedName("code")
    private int mCode;
    @SerializedName("error")
    private String mErrMessage;

    public int getCode() {
        return mCode;
    }

    public String getErrMessage() {
        return mErrMessage;
    }
}
