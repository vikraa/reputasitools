package com.labs.tools.model;

/**
 * Created by Vikraa on 12/22/2015.
 */
public class ContactSyncResultModel {
    private int mContactCount;
    private int mSuccessCount;
    private int mFailedCount;

    public int getContactCount() {
        return mContactCount;
    }

    public void setContactCount(int mContactCount) {
        this.mContactCount = mContactCount;
    }

    public int getSuccessCount() {
        return mSuccessCount;
    }

    public void setSuccessCount(int mSuccessCount) {
        this.mSuccessCount = mSuccessCount;
    }

    public int getFailedCount() {
        return mFailedCount;
    }

    public void setFailedCount(int mFailedCount) {
        this.mFailedCount = mFailedCount;
    }
}
