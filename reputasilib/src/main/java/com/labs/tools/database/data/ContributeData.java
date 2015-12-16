package com.labs.tools.database.data;

/**
 * Created by vikraa on 12/1/2015.
 */
public class ContributeData extends BaseData {
    private String mName;
    private String mNumber;
    private String mNotes;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String mNotes) {
        this.mNotes = mNotes;
    }
}
