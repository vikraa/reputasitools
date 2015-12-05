package com.labs.tools.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static String generateMd5(String input) {
        String result = "";
        MessageDigest diggest = null;
        try {
            diggest = MessageDigest.getInstance("MD5");
            diggest.update(input.getBytes());
            byte[] diggestBytes = diggest.digest();
            StringBuffer buffString = new StringBuffer();
            for (int i = 0; i < diggestBytes.length; i++)  {
                buffString.append(Integer.toHexString(0xFF & diggestBytes[i]));
            }
            return buffString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


}
