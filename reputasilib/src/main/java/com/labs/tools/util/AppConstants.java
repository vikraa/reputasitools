package com.labs.tools.util;

/**
 * Created by vikraa on 1/4/2016.
 */
public class AppConstants {

    /* caller state */
    public static final int STATE_CALLER_RINGING = 1;
    public static final int STATE_CALLER_OFFHOOK = 2;
    public static final int STATE_CALLER_IDLE = 0;

    /* caller data key */
    public static final String KEY_INCOMING_NUMBER = "incoming_number";

    /* search number type */
    public static final int SEARCH_TYPE_INCOMING_CALL_NUMBER = 1;
    public static final int SEARCH_TYPE_USER_REQUEST_NUMBER = 2;

}
