package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/16/2015.
 */
public class ContactRequest extends BaseRequest {
    @SerializedName("contactName")
    private String mName;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;

    public ContactRequest() {
    }

    public ContactRequest(String name, String phoneNumber) {
        this.mName = name;
        this.mPhoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }
}
