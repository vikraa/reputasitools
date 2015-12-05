package com.labs.tools.net.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/5/2015.
 */
public class LoginResponse extends BaseResponse {
    @SerializedName("sessionToken")
    private String mSessionToken;

    public String getSessionToken() {
        return mSessionToken;
    }
}
