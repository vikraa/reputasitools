package com.labs.tools.database.data;

/**
 * Created by Vikraa on 11/26/2015.
 */
public class ContactData extends BaseData {
    private String mId;
    private String mName;
    private String mNumber;
    private String mEmail;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }
}
