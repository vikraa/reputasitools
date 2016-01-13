package com.labs.tools.api;

import android.content.Context;
import android.database.Cursor;

import com.labs.tools.MyApplication;
import com.labs.tools.R;
import com.labs.tools.callback.Callback;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.ContributeData;
import com.labs.tools.database.table.TableContribute;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.ContributeRequest;
import com.labs.tools.net.response.ContributeResponse;
import com.labs.tools.throwable.ContributeException;
import com.labs.tools.throwable.ContributeExistException;
import com.labs.tools.util.NetworkUtils;
import com.labs.tools.util.TimeUtils;

import java.util.UUID;

import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vikraa on 12/13/2015.
 */
public class ContributeApi extends BaseApi<Void, Callback<Void>> {

    private Context mContext;
    private RetrofitHelper mRetrofit;

    public ContributeApi(Context context) {
        this.mContext = context;
        mRetrofit = new RetrofitHelper();
    }

    public void addContribute(String name, String number, String notes, String categoryId, final Callback<Void> callback) {
        final TableContribute tableContribute = new TableContribute();
        ContributeData data = tableContribute.find(new String[]{TableContribute.FIELD_CONTRIBUTE_NUMBER}, number);

        final ContributeData contributeData = new ContributeData();
        contributeData.setId(UUID.randomUUID().toString());
        contributeData.setName(name);
        contributeData.setNotes(notes);
        contributeData.setNumber(number);
        contributeData.setLastUpdated(TimeUtils.getCurrentTimestamp());
        contributeData.setSynchronizedStatus(TableContribute.STATUS_SYNCHRONIZED_FAILED);
        tableContribute.insert(contributeData);

        if (data == null) {
            if (NetworkUtils.isNetworkConnected(mContext)) {
                ContributeRequest request = new ContributeRequest();
                request.setName(name);
                request.setNumber(number);
                request.setNotes(notes);
                request.setCategoryId(categoryId);
                mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                        request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                        request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(mContext).getSessionToken());
                    }
                },MyApplication.getLogLevel()).contributeNumber(request, new retrofit.Callback<ContributeResponse>() {
                    @Override
                    public void success(ContributeResponse contributeResponse, Response response) {
                        contributeData.setSynchronizedStatus(TableContribute.STATUS_SYNCHRONIZED_SUCCESS);
                        tableContribute.update(contributeData);
                        if (callback != null) {
                            callback.onSuccess(null);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        contributeData.setSynchronizedStatus(TableContribute.STATUS_SYNCHRONIZED_SUCCESS);
                        tableContribute.update(contributeData);
                        if (callback != null) {
                            callback.onError(new ContributeException(error.getResponse().getReason()));
                        }
                    }
                });
            } else {
                if (callback != null) {
                    callback.onError(new ContributeException(mContext.getString(R.string.no_network_connection)));
                }
            }
        } else {
            if (callback != null) {
                callback.onError(new ContributeExistException(mContext.getString(R.string.item_already_exists)));
            }
        }
    }

    public boolean isNumberContributed(String number) {
        Cursor cursor = mContext.getContentResolver().query(DataProvider.CONTRIBUTE_URI, null, TableContribute.FIELD_CONTRIBUTE_NUMBER + " = ? ", new String[] { number }, null);
        boolean result = false;
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                result = true;
            }
            cursor.close();
        }
        return result;
    }
}
