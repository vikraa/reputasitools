package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.MyApplication;
import com.labs.tools.callback.Callback;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.BlockedNumberData;
import com.labs.tools.database.table.TableBlockedNumber;
import com.labs.tools.net.Response.BlockedNumberResponse;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.BlockedNumberRequest;
import com.labs.tools.throwable.BlockedNumberListException;
import com.labs.tools.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vikraa on 12/5/2015.
 */
public class BlockedNumberApi extends BaseApi<BlockedNumberRequest, Callback<List<BlockedNumberData>>> {
    private Context mContext;
    private RetrofitHelper mRetrofit;

    public BlockedNumberApi(Context context) {
        this.mContext = context;
        mRetrofit = new RetrofitHelper();
    }

    @Override
    public void getDataAsync(final Callback<List<BlockedNumberData>> callback) {
        super.getDataAsync(callback);
        BlockedNumberRequest request = new BlockedNumberRequest();
        mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(mContext).getSessionToken());
            }
        }, MyApplication.getLogLevel()).blockedNumberList(request, new retrofit.Callback<BlockedNumberResponse>() {
            @Override
            public void success(BlockedNumberResponse blockedNumberResponse, Response response) {
                List<BlockedNumberData> blockedNumberList = new ArrayList<BlockedNumberData>();
                List<BlockedNumberResponse.Data> dataList = blockedNumberResponse.getData();
                if (dataList.size() > 0) {
                    for (BlockedNumberResponse.Data br : dataList) {
                        BlockedNumberData numberData = new BlockedNumberData();
                        numberData.setName(br.getNumberOwner());
                        numberData.setNumber(br.getNumber());
                        numberData.setCategoryId(br.getCategoryId());
                        numberData.setId(UUID.randomUUID().toString());
                        numberData.setSynchronizedStatus(TableBlockedNumber.STATUS_SYNCHRONIZED_SUCCESS);
                        numberData.setLastUpdated(TimeUtils.getCurrentTimestamp());
                        blockedNumberList.add(numberData);
                    }
                    mContext.getApplicationContext().getContentResolver().delete(DataProvider.BLOCKEDNUMBER_URI, null, null);
                    TableBlockedNumber tableBlockedNumber = new TableBlockedNumber();
                    tableBlockedNumber.insert(blockedNumberList);
                }
                callback.onSuccess(blockedNumberList);
            }

            @Override
            public void failure(RetrofitError error) {
                BlockedNumberListException exception = new BlockedNumberListException(error.getResponse().getReason());
                callback.onError(exception);
            }
        });
    }
}
