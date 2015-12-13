package com.labs.tools.throwable;

import android.accounts.NetworkErrorException;

/**
 * Created by vikraa on 12/13/2015.
 */
public class NetworkException extends NetworkErrorException {
    public NetworkException(String message) {
        super(message);
    }
}
