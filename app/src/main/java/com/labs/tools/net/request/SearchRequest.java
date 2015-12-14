package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/14/2015.
 */
public class SearchRequest extends BaseRequest {
    @SerializedName("incomingNumber")
    private String mNumber;

    public SearchRequest() {
    }

    public SearchRequest(String number) {
        this.mNumber = number;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }
}
