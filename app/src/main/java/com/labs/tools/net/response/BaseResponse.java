package com.labs.tools.net.response;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by vikraa on 12/5/2015.
 */
public abstract class BaseResponse {
    @SerializedName("objectId")
    private String mObjectId;
    @SerializedName("updatedAt")
    private String mUpdatedAt;
    @SerializedName("createdAt")
    private String mCreatedAt;
    @SerializedName("ACL")
    private HashMap<String, HashMap<String, Boolean>> mACL;

    public String getObjectId() {
        return mObjectId;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public HashMap<String, HashMap<String, Boolean>> getACL() {
        return mACL;
    }
}
