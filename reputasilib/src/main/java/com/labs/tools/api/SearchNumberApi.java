package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.MyApplication;
import com.labs.tools.R;
import com.labs.tools.callback.Callback;
import com.labs.tools.database.data.ContactData;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.SearchRequest;
import com.labs.tools.net.response.SearchResponse;
import com.labs.tools.throwable.SearchNumberException;
import com.labs.tools.util.NetworkUtils;

import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vikraa on 12/13/2015.
 */
public class SearchNumberApi extends BaseApi<Void, Callback<Void>> {
    private Context mContext;
    private RetrofitHelper mRetrofit;

    public SearchNumberApi(Context context) {
        this.mContext = context;
        mRetrofit = new RetrofitHelper();
    }

    public void search(String number, final Callback<ContactData> callback) {
        if (NetworkUtils.isNetworkConnected(mContext)) {
            SearchRequest request = new SearchRequest(number);
            mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(mContext).getSessionToken());
                }
            }, MyApplication.getLogLevel()).searchNumber(request, new retrofit.Callback<SearchResponse>() {
                @Override
                public void success(SearchResponse searchResponse, Response response) {
                    ContactData contactData = new ContactData();
                    if (callback != null) {
                        callback.onSuccess(contactData);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (callback != null) {
                        callback.onError(new SearchNumberException(error.getResponse().getReason()));
                    }
                }
            });
        } else {
            callback.onError(new SearchNumberException(MyApplication.getContext().getString(R.string.no_network_connection)));
        }
    }

    public String[] getSearchHistories() {
        return Preferences.getInstance(mContext).getSearchHistories();
    }

    public void addToSearchHistories(String number) {
        Preferences.getInstance(mContext).addSearchHistory(number);
    }
}
