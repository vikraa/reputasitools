package com.labs.tools.callback;

/**
 * Created by vikraa on 12/5/2015.
 */
public interface Callback<T> {
    void onSuccess(T result);
    void onError(Throwable errorResult);
}
