package com.labs.tools.database.data;

/**
 * Created by vikraa on 11/29/2015.
 */
public abstract class BaseData {
    private long mLastUpdated;
    private String mId;
    private int mSynchronizedStatus;

    public int getSynchronizedStatus() {
        return mSynchronizedStatus;
    }

    public void setSynchronizedStatus(int synchronizedStaus) {
        this.mSynchronizedStatus = synchronizedStaus;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public long getLastUpdated() {
        return mLastUpdated;
    }

    public void setLastUpdated(long mLastUpdated) {
        this.mLastUpdated = mLastUpdated;
    }
}
