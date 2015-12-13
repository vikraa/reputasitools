package com.labs.tools.net.response1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vikraa on 12/5/2015.
 */
public class BlockedNumberResponse extends BaseResponse {

    @SerializedName("result")
    private List<Data> mData;

    public List<Data> getData() {
        return mData;
    }

    public static class Data {
        @SerializedName("username")
        private String mUserName;
        @SerializedName("phoneNumber")
        private String mNumber;
        @SerializedName("numberName")
        private String mNumberOwner;
        @SerializedName("category")
        private String mCategoryId;
        @SerializedName("specialFlag")
        private int mFlagType;

        public String getUserName() {
            return mUserName;
        }

        public String getNumber() {
            return mNumber;
        }

        public String getNumberOwner() {
            return mNumberOwner;
        }

        public String getCategoryId() {
            return mCategoryId;
        }

        public int getFlagType() {
            return mFlagType;
        }

    }

}
