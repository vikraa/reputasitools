package com.labs.tools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.labs.tools.util.IntentUtils;

/**
 * Created by vikraa on 12/13/2015.
 */
public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                handleIncomingSms(bundle);
            }
        }
    }

    private synchronized void handleIncomingSms(Bundle bundle) {
        Object[] pduArray = (Object[])bundle.get("pdus");
        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pduArray[0]);
        String sender = currentMessage.getDisplayOriginatingAddress();
        String message = currentMessage.getDisplayMessageBody();
    }
}
