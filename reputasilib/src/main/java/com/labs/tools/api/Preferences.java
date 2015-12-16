package com.labs.tools.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by vikraa on 12/5/2015.
 */
public class Preferences {
    private static final Object LOCK = new Object();
    private static Preferences sInstance;
    private static final String FILE_NAME = "reputasi_pref";

    private static final String PREF_SESSION_TOKEN = "sessiontoken";
    private static final String PREF_DISCLAIMER_AGREEMENT = "disclaimeragreement";
    private static final String PREF_SEARCH_HISTORIES = "histories_";
    private final int MAXIMUM_HISTORIES = 3;

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

    public void addSearchHistory(String number) {
        String[] oldHistories = getSearchHistories();
        String[] newHistories = new String[MAXIMUM_HISTORIES];
        for (int i = 0; i < MAXIMUM_HISTORIES; i++) {
            if (i == 0) {
                newHistories[i] = number;
            } else {
                newHistories[i] = oldHistories[i - 1];
            }
        }
        setSearchHistories(newHistories);
    }

    public String[] getSearchHistories() {
        String[] result = new String[MAXIMUM_HISTORIES];
        for (int i = 0; i < MAXIMUM_HISTORIES; i++) {
            result[i] = mSharedPreferences.getString(PREF_SEARCH_HISTORIES + i, "");
        }
        return result;
    }

    public void setSearchHistories(String[] histories) {
        int countHistories = histories.length;
        int count = 0;
        for (String val : histories) {
            if (count < MAXIMUM_HISTORIES) {
                mSharedPreferences.edit().putString(PREF_SEARCH_HISTORIES + count, val).commit();
                count++;
            }
        }
    }
}
