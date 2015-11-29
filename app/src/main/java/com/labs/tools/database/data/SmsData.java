package com.labs.tools.database.data;

/**
 * Created by vikraa on 11/29/2015.
 */
public class SmsData extends BaseData {
    private String mSender;
    private String mMessage;
    private int mBlockedStatus;

    public int getBlockedStatus() {
        return mBlockedStatus;
    }

    public void setBlockedStatus(int blockedStatus) {
        this.mBlockedStatus = blockedStatus;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        this.mSender = sender;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }
}
