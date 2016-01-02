package com.labs.tools.receiver;

/**
 * Created by vikraa on 1/2/2016.
 */
public class ReceiverConstants {
    /* general app action intent */
    public static final String ACTION_APP_WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
    public static final String ACTION_APP_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String ACTION_APP_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
    /* caller action intent */
    public static final String ACTION_CALLER_INCOMING_CALL = "android.intent.action.PHONE_STATE";
    /* sms action intent */
    public static final String ACTION_SMS_INCOMING_MESSAGES = "android.provider.Telephony.SMS_RECEIVED";
}
