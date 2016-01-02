package com.labs.tools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class AppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(ReceiverConstants.ACTION_APP_BOOT_COMPLETED)) {

            } else if (intent.getAction().equalsIgnoreCase(ReceiverConstants.ACTION_APP_CONNECTIVITY_CHANGE)) {

            } else if (intent.getAction().equalsIgnoreCase(ReceiverConstants.ACTION_APP_WIFI_STATE_CHANGED)) {

            }
        }
    }
}
