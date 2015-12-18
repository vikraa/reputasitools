package com.labs.tools.net.response.item;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class SpecialNumberItem extends BaseCloudItem {
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("specialFlag")
    private String mSpecialFlag;
    @SerializedName("username")
    private String mUserName;

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public String getSpecialFlag() {
        return mSpecialFlag;
    }

    public String getUserName() {
        return mUserName;
    }
}
