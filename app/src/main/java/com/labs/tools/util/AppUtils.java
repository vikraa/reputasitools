package com.labs.tools.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vikraa on 11/20/2015.
 */
public class AppUtils {

    public static String timestampLongToStringFormat(String format, long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(timeStamp);
        return sdf.format(date);
    }

    public static String dateObjectToStringFormat(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
