package com.labs.tools.model;

import com.labs.tools.database.data.ContactData;

import java.util.List;

/**
 * Created by vikraa on 12/20/2015.
 */
public class ContactModel {
    private List<ContactData> mContactList;
    private String mHash;

    public List<ContactData> getContactList() {
        return mContactList;
    }

    public void setContactList(List<ContactData> mContactList) {
        this.mContactList = mContactList;
    }

    public String getHash() {
        return mHash;
    }

    public void setHash(String mHash) {
        this.mHash = mHash;
    }
}
