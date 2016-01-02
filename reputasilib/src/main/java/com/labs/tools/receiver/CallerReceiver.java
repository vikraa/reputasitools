package com.labs.tools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by vikraa on 12/13/2015.
 */
public class CallerReceiver extends BroadcastReceiver {

    private static Listener mListener;

    public static void setCallerListener(Listener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(ReceiverConstants.ACTION_CALLER_INCOMING_CALL)) {

            }
        }
    }


    public interface Listener {
        void onCallRinging(String number);
        void onCallPickup();
        void onCallRejected(String number);
        void onCallFinished();
    }
}
