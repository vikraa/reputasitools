package com.labs.tools;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.labs.tools.api.CallerApi;
import com.labs.tools.api.Preferences;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.SearchRequest;
import com.labs.tools.net.response.SearchResponse;
import com.labs.tools.util.AppConstants;
import com.labs.tools.util.IntentUtils;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vikraa on 12/18/2015.
 */
public class MyServices extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(IntentUtils.ACTION_REQUEST_NUMBER_RESOLVER)) {
                CallerApi.getInstance(this).resolveNumber(intent.getExtras().getString(AppConstants.KEY_INCOMING_NUMBER));
            }
        }
        return Service.START_STICKY;
        //super.onStartCommand(intent, flags, startId);
    }

/*    private void handlerNumberResolver(Intent intent) {
        SearchRequest request = new SearchRequest();
        request.setNumber(intent.getExtras().getString(AppConstants.KEY_INCOMING_NUMBER));

        mRetrofitHelper.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(getApplicationContext()).getSessionToken());
            }
        },MyApplication.getLogLevel()).searchNumber(request, new Callback<SearchResponse>() {
            @Override
            public void success(SearchResponse searchResponse, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }*/

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
