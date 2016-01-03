package com.labs.tools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.labs.tools.api.CallerApi;
import com.labs.tools.callback.CallerCallback;
import com.labs.tools.util.IntentUtils;

/**
 * Created by vikraa on 12/13/2015.
 */
public class CallerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            prepareCallerState(context);
        }
    }

    private void prepareCallerState(Context context) {
        try {
            TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            CallerState callerState = new CallerState(context);
            tmgr.listen(callerState,PhoneStateListener.LISTEN_CALL_STATE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class CallerState extends PhoneStateListener {
        private CallerApi mCallerApi;
        private CallerCallback mCallerListener;
        private Context mContext;

        public CallerState(Context context) {
            mContext = context;
            mCallerApi = CallerApi.getInstance(context);
            mCallerListener = mCallerApi.getListener();
        }

        private void requestNumberResolving(String number) {
            Intent serviceIntent = new Intent(IntentUtils.ACTION_REQUEST_NUMBER_RESOLVER);
            serviceIntent.putExtra(IntentUtils.KEY_INCOMING_NUMBER, number);
            mContext.startService(serviceIntent);
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case IntentUtils.STATE_CALLER_IDLE:
                    if (mCallerListener != null) {
                        mCallerListener.onCallFinished();
                    }
                    break;
                case IntentUtils.STATE_CALLER_RINGING:

                    if (mCallerListener != null) {
                        mCallerListener.onCallRinging(incomingNumber);
                    }
                    break;
                case IntentUtils.STATE_CALLER_OFFHOOK:
                    if (mCallerListener != null) {
                        mCallerListener.onCallPickup();
                    }
                    break;
            }
        }
    }


}
