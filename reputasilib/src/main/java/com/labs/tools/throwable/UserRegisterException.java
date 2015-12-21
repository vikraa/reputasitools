package com.labs.tools.throwable;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class UserRegisterException extends RuntimeException {
    public UserRegisterException(String detailMessage) {
        super(detailMessage);
    }
}
