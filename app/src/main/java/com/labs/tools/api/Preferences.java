package com.labs.tools.api;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vikraa on 12/5/2015.
 */
public class Preferences {
    private static final Object LOCK = new Object();
    private static Preferences sInstance;
    private static final String FILE_NAME = "reputasi_pref";

    private static final String PREF_SESSION_TOKEN = "sessiontoken";
    private static final String PREF_DISCLAIMER_AGREEMENT = "disclaimeragreement";

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public static Preferences getInstance(Context context) {
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new Preferences(context);
            }
        }
        return sInstance;
    }

    public Preferences(Context context) {
        mContext = context.getApplicationContext();
        mSharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void putSession(String sessionToken) {
        mSharedPreferences.edit().putString(PREF_SESSION_TOKEN, sessionToken).commit();
    }

    public String getSessionToken() {
        return mSharedPreferences.getString(PREF_SESSION_TOKEN, "");
    }

    public void setAgreeDisclaimer(boolean agree) {
        mSharedPreferences.edit().putBoolean(PREF_DISCLAIMER_AGREEMENT, agree);
    }

    public boolean isAgreeDisclaimer() {
        return mSharedPreferences.getBoolean(PREF_DISCLAIMER_AGREEMENT, false);
    }
}
