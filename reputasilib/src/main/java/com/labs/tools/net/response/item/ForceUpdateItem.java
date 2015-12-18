package com.labs.tools.net.response.item;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class ForceUpdateItem {
    @SerializedName("forceUpdate")
    private boolean mForceUpdate;

    public boolean isForceUpdate() {
        return mForceUpdate;
    }
}
