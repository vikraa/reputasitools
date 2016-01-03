package com.labs.tools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.labs.tools.util.IntentUtils;

/**
 * Created by vikraa on 12/13/2015.
 */
public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(IntentUtils.ACTION_SMS_INCOMING_MESSAGES)) {

            }
        }
    }
}
