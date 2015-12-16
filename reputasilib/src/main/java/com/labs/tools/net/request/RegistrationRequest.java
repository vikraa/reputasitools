package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/5/2015.
 */
public class RegistrationRequest extends BaseRequest {
    @SerializedName("username")
    private String mUserName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("authData")
    private String mAuthData;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("deviceInfo")
    private String mDeviceInfo;
    @SerializedName("simcardId")
    private String mSimcardId;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("gender")
    private String mGender;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getAuthData() {
        return mAuthData;
    }

    public void setAuthData(String mAuthData) {
        this.mAuthData = mAuthData;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String mDisplayName) {
        this.mDisplayName = mDisplayName;
    }

    public String getDeviceInfo() {
        return mDeviceInfo;
    }

    public void setDeviceInfo(String mDeviceInfo) {
        this.mDeviceInfo = mDeviceInfo;
    }

    public String getSimcardId() {
        return mSimcardId;
    }

    public void setSimcardId(String mSimcardId) {
        this.mSimcardId = mSimcardId;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }
}
