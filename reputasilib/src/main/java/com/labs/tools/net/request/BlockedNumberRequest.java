package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/5/2015.
 */
public class BlockedNumberRequest extends BaseRequest {
    @SerializedName("incomingNumber")
    private String mNumber;

    public void setNumber(String number) {
        this.mNumber = number;
    }
}
