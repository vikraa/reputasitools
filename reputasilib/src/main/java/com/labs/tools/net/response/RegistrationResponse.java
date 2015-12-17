package com.labs.tools.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/5/2015.
 */
public class RegistrationResponse extends BaseParseResponse {
    @SerializedName("sessionToken")
    private String mSessionToken;

    public String getSessionToken() {
        return mSessionToken;
    }
}
