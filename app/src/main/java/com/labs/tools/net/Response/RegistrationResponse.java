package com.labs.tools.net.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/5/2015.
 */
public class RegistrationResponse extends BaseResponse {
    @SerializedName("sessionToken")
    private String mSessionToken;

    public String getSessionToken() {
        return mSessionToken;
    }
}
