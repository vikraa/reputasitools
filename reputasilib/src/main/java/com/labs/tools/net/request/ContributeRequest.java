package com.labs.tools.net.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/14/2015.
 */
public class ContributeRequest extends BaseRequest {
    @SerializedName("name")
    private String mName;
    @SerializedName("number")
    private String mNumber;
    @SerializedName("notes")
    private String mNotes;
    @SerializedName("categoryId")
    private String mCategoryId;
    
    public ContributeRequest() {
        
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

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        this.mNotes = notes;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        this.mCategoryId = categoryId;
    }
}
