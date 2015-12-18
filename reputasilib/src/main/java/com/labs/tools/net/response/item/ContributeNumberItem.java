package com.labs.tools.net.response.item;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class ContributeNumberItem extends BaseCloudItem {
    @SerializedName("category")
    private String mCategoryId;
    @SerializedName("noOfRequests")
    private int mRequestCounts;
    @SerializedName("ownerAddress")
    private String mOwnerAddress;
    @SerializedName("ownerName")
    private String mOwnerName;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("score")
    private int mScore;
    @SerializedName("thumbDown")
    private int mThumbDown;
    @SerializedName("thumbUp")
    private int mThumbUp;

    public String getCategoryId() {
        return mCategoryId;
    }

    public int getRequestCounts() {
        return mRequestCounts;
    }

    public String getOwnerAddress() {
        return mOwnerAddress;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public int getScore() {
        return mScore;
    }

    public int getThumbDown() {
        return mThumbDown;
    }

    public int getThumbUp() {
        return mThumbUp;
    }
}
