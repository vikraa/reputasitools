package com.labs.tools.net.response.item;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikraa on 12/17/2015.
 */
public class SearchNumberItem extends BaseItem {
    @SerializedName("category")
    private String mCategoryId;
    @SerializedName("noOfRequests")
    private int mNumberOfRequests;
    @SerializedName("ownerAddress")
    private String mOwnerAddress;
    @SerializedName("ownerName")
    private String mOwnerName;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("score")
    private int mScore;
    @SerializedName("thumbDown")
    private int mThumbDownScore;
    @SerializedName("thumbUp")
    private int mThumbUpScore;

    public String getCategoryId() {
        return mCategoryId;
    }

    public int getNumberOfRequests() {
        return mNumberOfRequests;
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

    public int getThumbDownScore() {
        return mThumbDownScore;
    }

    public int getThumbUpScore() {
        return mThumbUpScore;
    }
}
