package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 11/20/2015.
 */
public abstract class BaseRequest {

    @SerializedName("timestamp")
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public BaseRequest(){
        timestamp = System.currentTimeMillis();
    }
}
