package com.labs.tools.database.data;

/**
 * Created by vikraa on 11/29/2015.
 */
public class CallData extends BaseData {
    private String mFromName;
    private String mNumber;
    private long mCallTimestamp;
    private int mCallType;

    public String getFromName() {
        return mFromName;
    }

    public void setFromName(String mFromName) {
        this.mFromName = mFromName;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public long getCallTimestamp() {
        return mCallTimestamp;
    }

    public void setCallTimestamp(long mCallTimestamp) {
        this.mCallTimestamp = mCallTimestamp;
    }

    public int getCallType() {
        return mCallType;
    }

    public void setCallType(int callType) {
        this.mCallType = callType;
    }
}
