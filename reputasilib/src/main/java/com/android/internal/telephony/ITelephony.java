package com.android.internal.telephony;

/**
 * Created by vikraa on 1/9/2016.
 */
public interface ITelephony {
    boolean endCall();
    void answerRingingCall();
    void silenceRinger();
}