package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/14/2015.
 */
public class SearchRequest extends BaseRequest {
    @SerializedName("incomingNumber")
    private String mNumber;
    @SerializedName("callType")
    private int mSearchType;
    @SerializedName("connectionType")
    private String mConnectionType;

    public SearchRequest() {
    }

    public SearchRequest(String number) {
        this.mNumber = number;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    public void setSearchType(int searchType) {
        this.mSearchType = searchType;
    }

    public void setConnectionType(String connectionType) {
        this.mConnectionType = connectionType;
    }
}
