package com.labs.tools.net.response.item;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class ContactHashItem {
    @SerializedName("message")
    private String mMessage;
    @SerializedName("needupdate")
    private boolean mContactNeedUpdate;

    public String getmMessage() {
        return mMessage;
    }

    public boolean isContactNeedUpdate() {
        return mContactNeedUpdate;
    }
}
