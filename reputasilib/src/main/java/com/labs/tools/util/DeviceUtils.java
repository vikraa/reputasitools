package com.labs.tools.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.labs.tools.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vikraa on 11/20/2015.
 */
public class DeviceUtils {

    public static final String DENSITY_LDPI = "ldpi";
    public static final String DENSITY_MDPI = "mdpi";
    public static final String DENSITY_HDPI = "hdpi";
    public static final String DENSITY_XHDPI = "xhdpi";
    public static final String DENSITY_XXHDPI = "xxhdpi";
    public static final String DENSITY_XXXHDPI = "xxxhdpi";
    public static final String SIM_CARD_AVAILABLE = "sim_card_available";
    public static final String SIM_CARD_NOT_AVAILABLE = "sim_card_not_available";

    public static String getImei(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String getMacAddress(Context context) {
        WifiInfo wifiInfo = ((WifiManager)context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
        if (wifiInfo != null) {
            return wifiInfo.getMacAddress();
        }
        return null;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static int getAndroidBuildVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getCarrierName(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int mnc;
        try {
            mnc = Integer.parseInt(manager.getNetworkOperator().substring(3));
        } catch (Exception e) {
            return "UNKNOWN";
        }
        switch (mnc) {
            case 1:
                return "INDOSAT";
            case 3:
                return "STARONE";
            case 7:
                return "TELKOM FLEXY";
            case 8:
                return "AXIS";
            case 9:
                return "SMART FREN";
            case 10:
                return "TELKOMSEL";
            case 11:
                return "XL";
            case 20:
                return "TELKOM MOBILE";
            case 21:
                return "IM3";
            case 27:
                return "CERIA";
            case 28:
                return "FREN";
            case 88:
                return "BOLT";
            case 89:
                return "3";
            case 99:
                return "ESIA";
            case 995:
            case 996:
                return "KOMSELINDO";
            default:
                return "UNKNOWN";
        }
    }

    public static String getScreenDensity(int density) {
        String densityType;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                densityType = DENSITY_LDPI;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                densityType = DENSITY_MDPI;
                break;
            case DisplayMetrics.DENSITY_HIGH:
            case DisplayMetrics.DENSITY_TV:
                densityType = DENSITY_HDPI;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                densityType = DENSITY_XHDPI;
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
            case DisplayMetrics.DENSITY_400:
                densityType = DENSITY_XXHDPI;
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
            case DisplayMetrics.DENSITY_560:
                densityType = DENSITY_XXXHDPI;
                break;
            default:
                densityType = DENSITY_XHDPI;
        }
        return densityType;
    }

    public static String getSimCardStatus(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telephonyManager.getSimState();
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
            case TelephonyManager.SIM_STATE_UNKNOWN:
                return SIM_CARD_NOT_AVAILABLE;
            default:
                return SIM_CARD_AVAILABLE;
        }
    }

    public static String getSimCardId() {
        TelephonyManager tmgr = (TelephonyManager)MyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String simICCID = tmgr.getSimSerialNumber();
        String simISDN = tmgr.getLine1Number() == null ? "" : tmgr.getLine1Number();

        if (simISDN == null) {
            return simICCID;
        } else if (simISDN != null && !simISDN.isEmpty() ) {
            return simISDN;
        } else {
            return simICCID;
        }

    }

    public static String getDeviceInfo() {
        String phoneInfo = "";
        String osVersion = Build.VERSION.RELEASE;
        String deviceManufactur = Build.MANUFACTURER;
        String deviceModel = Build.MODEL;
        TelephonyManager tmgr = (TelephonyManager)MyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceImei = tmgr.getDeviceId();
        String simICCID = tmgr.getSimSerialNumber();
        String simISDN = tmgr.getLine1Number() == null ? "" : tmgr.getLine1Number();

        JSONObject jsonParent = new JSONObject();
        try {
            jsonParent.put("osVersion", osVersion);
            jsonParent.put("manufactur", deviceManufactur);
            jsonParent.put("model", deviceModel);
            jsonParent.put("imei:", deviceImei);
            jsonParent.put("iccid", simICCID);
            jsonParent.put("isdn", simISDN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        phoneInfo = jsonParent.toString();
        return phoneInfo;
    }
}
