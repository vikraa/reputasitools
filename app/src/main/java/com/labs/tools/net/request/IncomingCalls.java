package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 11/20/2015.
 */
public class IncomingCalls extends BaseRequest {

    @SerializedName("incomingNumber")
    private String mIncomingNumbers;

    public String getIncomingNumber() {
        return mIncomingNumbers;
    }

    public void setIncomingNumber(String incomingNumbers) {
        this.mIncomingNumbers = incomingNumbers;
    }

}
