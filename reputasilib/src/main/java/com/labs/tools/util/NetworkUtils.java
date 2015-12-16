package com.labs.tools.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    public static String NETWORK_TYPE_MOBILE = "mobile_network";
    public static String NETWORK_TYPE_WIFI = "wifi_network";
    public static String NETWORK_TYPE_UNKNOWN = "unknown_network";

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static String getNetworkConnectionType(Context context) {
        int networkType = getNetworkInfo(context).getType();
        if (networkType == ConnectivityManager.TYPE_MOBILE) {
            return  NETWORK_TYPE_MOBILE;
        } else if (networkType == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_TYPE_WIFI;
        }
        return NETWORK_TYPE_UNKNOWN;
    }

}
