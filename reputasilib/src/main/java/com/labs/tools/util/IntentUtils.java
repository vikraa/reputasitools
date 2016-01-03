package com.labs.tools.util;

/**
 * Created by vikraa on 1/2/2016.
 */
public class IntentUtils {

    /* general app action intent */
    public static final String ACTION_APP_WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
    public static final String ACTION_APP_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String ACTION_APP_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

    /* caller action intent */
    public static final String ACTION_CALLER_INCOMING_CALL = "android.intent.action.PHONE_STATE";

    /* sms action intent */
    public static final String ACTION_SMS_INCOMING_MESSAGES = "android.provider.Telephony.SMS_RECEIVED";

    /* action services */
    public static final String ACTION_REQUEST_NUMBER_RESOLVER = "reputasi.request.number.resolver";


    /* caller state */
    public static final int STATE_CALLER_RINGING = 1;
    public static final int STATE_CALLER_OFFHOOK = 2;
    public static final int STATE_CALLER_IDLE = 0;

    /* caller data key */
    public static final String KEY_INCOMING_NUMBER = "incoming_number";


}
