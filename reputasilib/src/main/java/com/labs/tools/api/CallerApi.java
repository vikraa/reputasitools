package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.MyApplication;
import com.labs.tools.callback.Callback;
import com.labs.tools.callback.CallerCallback;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.SearchRequest;
import com.labs.tools.net.response.SearchResponse;
import com.labs.tools.net.response.item.SearchNumberItem;
import com.labs.tools.util.AppConstants;
import com.labs.tools.util.NetworkUtils;

import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vikraa on 12/13/2015.
 */
public class CallerApi extends BaseApi<Void, Callback<Void>> {

    private Context mContext;
    private CallerCallback mListener;
    private RetrofitHelper mRetrofitHelper;
    private static CallerApi sInstance;
    private static final int DEFAULT_SCORE = 60;

    public static CallerApi getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CallerApi(context);
        }
        return sInstance;
    }

    private CallerApi(Context context) {
        this.mContext = context;
    }

    public void setListener(CallerCallback listener) {
        this.mListener = listener;
    }

    public CallerCallback getListener() {
        return this.mListener;
    }

    public void resolveNumber(final String number) {
        SearchRequest request = new SearchRequest();
        request.setNumber(number);
        request.setConnectionType(NetworkUtils.getNetworkConnectionType(mContext));
        request.setSearchType(AppConstants.SEARCH_TYPE_INCOMING_CALL_NUMBER);
        mRetrofitHelper.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(mContext).getSessionToken());
            }
        }, MyApplication.getLogLevel()).searchNumber(request, new retrofit.Callback<SearchResponse>() {
            @Override
            public void success(SearchResponse searchResponse, Response response) {
                SearchNumberItem item = searchResponse.getResult();
                if (mListener != null) {
                    mListener.onNumberResult(item.getOwnerName(), item.getPhoneNumber(),
                            item.getThumbUpScore(), item.getThumbDownScore(), item.getScore());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mListener != null) {
                    mListener.onNumberResult(number, number, 0, 0, DEFAULT_SCORE);
                }
            }
        });
    }
}
