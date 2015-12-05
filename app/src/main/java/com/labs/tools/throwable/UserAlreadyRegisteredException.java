package com.labs.tools.throwable;

import retrofit.RetrofitError;

/**
 * Created by vikraa on 12/5/2015.
 */
public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(RetrofitError error) {
        super(error);
    }
}
