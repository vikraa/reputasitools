package com.labs.tools.throwable;

/**
 * Created by Vikraa on 12/14/2015.
 */
public class ContactReadException extends RuntimeException {
    public ContactReadException(String detailMessage) {
        super(detailMessage);
    }
}
