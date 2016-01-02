package com.labs.tools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by vikraa on 12/13/2015.
 */
public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(ReceiverConstants.ACTION_SMS_INCOMING_MESSAGES)) {

            }
        }
    }
}
