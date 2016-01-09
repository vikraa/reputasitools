package com.labs.tools.callback;

/**
 * Created by vikraa on 1/9/2016.
 */
public interface SmsCallback {
    void onNewSmsReceived(String sender);
    void onSmsRejected(String sender);
    void onReadInboxMessage();
    void onReadDraftMessage();
    void onReadSentMessage();
}
