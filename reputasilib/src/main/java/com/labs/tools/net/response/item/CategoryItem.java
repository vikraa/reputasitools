package com.labs.tools.net.response.item;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class CategoryItem extends BaseCloudItem {
    @SerializedName("categoryId")
    private String mId;
    @SerializedName("categoryName")
    private String mName;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
