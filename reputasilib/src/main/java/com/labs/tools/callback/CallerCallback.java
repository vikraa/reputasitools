package com.labs.tools.callback;

/**
 * Created by vikraa on 1/3/2016.
 */
public interface CallerCallback {
    void onNumberResult(String name, String number, int scoreUp, int scoreDown, int currentScore);
    void onCallRinging(String number);
    void onCallPickup();
    void onCallRejected(String number);
    void onCallFinished();
}
