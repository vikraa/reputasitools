package com.labs.tools.database.data;

/**
 * Created by vikraa on 12/1/2015.
 */
public class BlockedNumberData extends BaseData {
    private String mName;
    private String mNumber;
    private String mCategoryId;

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        this.mCategoryId = categoryId;
    }

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
}
