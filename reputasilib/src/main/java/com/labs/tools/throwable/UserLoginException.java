package com.labs.tools.throwable;

import retrofit.RetrofitError;

/**
 * Created by vikraa on 12/5/2015.
 */
public class UserLoginException extends RuntimeException {

    public UserLoginException(RetrofitError error) {
        super(error);
    }
}
