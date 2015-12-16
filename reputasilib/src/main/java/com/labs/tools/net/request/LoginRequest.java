package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/5/2015.
 */
public class LoginRequest extends BaseRequest {
    @SerializedName("username")
    private String mUserName;
    @SerializedName("password")
    private String mPassword;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
