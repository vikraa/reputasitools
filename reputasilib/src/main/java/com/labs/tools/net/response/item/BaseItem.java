package com.labs.tools.net.response.item;

import com.google.gson.annotations.SerializedName;
import com.labs.tools.net.response.BaseParseResponse;

/**
 * Created by vikraa on 12/17/2015.
 */
public abstract class BaseItem extends BaseParseResponse {
    @SerializedName("__type")
    private String mItemType;
    @SerializedName("className")
    private String mClassName;

    public String getItemType() {
        return mItemType;
    }
}
