package com.labs.tools.api;

import android.content.Context;

import com.labs.tools.MyApplication;
import com.labs.tools.R;
import com.labs.tools.callback.Callback;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.BlockedNumberData;
import com.labs.tools.database.table.TableBlockedNumber;
import com.labs.tools.net.response.BlockedNumberResponse;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.BlockedNumberRequest;
import com.labs.tools.throwable.BlockedNumberAddException;
import com.labs.tools.throwable.BlockedNumberListException;
import com.labs.tools.throwable.BlockedNumberRemoveException;
import com.labs.tools.throwable.NetworkException;
import com.labs.tools.util.NetworkUtils;
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
public class BlockedNumberApi extends BaseApi<BlockedNumberRequest, Callback> {
    private Context mContext;
    private RetrofitHelper mRetrofit;

    public BlockedNumberApi(Context context) {
        this.mContext = context;
        mRetrofit = new RetrofitHelper();
    }

    @Override
    public void getDataAsync(final Callback callback) {
        super.getDataAsync(callback);
        if (NetworkUtils.isNetworkConnected(mContext)) {
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
                    if (callback != null) {
                        callback.onSuccess(blockedNumberList);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (callback != null) {
                        BlockedNumberListException exception = new BlockedNumberListException(error.getResponse().getReason());
                        callback.onError(exception);
                    }
                }
            });
        } else {
            callback.onError(new NetworkException(MyApplication.getContext().getString(R.string.no_network_connection)));
        }
    }

    public void addBlockedNumber(String number, String name, String categoryId, final Callback<Void> callback) {
        if (NetworkUtils.isNetworkConnected(mContext)) {
            TableBlockedNumber tableBlockedNumber = new TableBlockedNumber();
            String[] fields = { TableBlockedNumber.FIELD_BLOCKED_NUMBER };
            BlockedNumberData findResult = tableBlockedNumber.find(fields, number);
            BlockedNumberRequest request = new BlockedNumberRequest();
            request.setNumber(number);

            if (findResult != null) {
                findResult.setNumber(number);
                findResult.setCategoryId(categoryId);
                findResult.setName(name);
                findResult.setLastUpdated(TimeUtils.getCurrentTimestamp());
                findResult.setSynchronizedStatus(TableBlockedNumber.STATUS_SYNCHRONIZED_FAILED);
                tableBlockedNumber.update(findResult);
            } else {
                BlockedNumberData data = new BlockedNumberData();
                data.setId(UUID.randomUUID().toString());
                data.setNumber(number);
                data.setName(name);
                data.setCategoryId(categoryId);
                data.setLastUpdated(TimeUtils.getCurrentTimestamp());
                data.setSynchronizedStatus(TableBlockedNumber.STATUS_SYNCHRONIZED_FAILED);
                tableBlockedNumber.insert(data);
            }

            mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(mContext).getSessionToken());
                }
            }, MyApplication.getLogLevel()).addBlockedNumber(request, new retrofit.Callback<BlockedNumberResponse>() {
                @Override
                public void success(BlockedNumberResponse blockedNumberResponse, Response response) {
                    if (callback != null) {
                        callback.onSuccess(null);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (callback != null) {
                        callback.onError(new BlockedNumberAddException(error.getResponse().getReason()));
                    }
                }
            });
        } else {
            callback.onError(new NetworkException(MyApplication.getContext().getString(R.string.no_network_connection)));
        }

    }

    public void removeBlockedNumber(final String number, final Callback<Void> callback) {
        if (NetworkUtils.isNetworkConnected(mContext)) {
            BlockedNumberRequest request = new BlockedNumberRequest();
            request.setNumber(number);
            mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(mContext).getSessionToken());
                }
            }, MyApplication.getLogLevel()).removeBlockedNumber(request, new retrofit.Callback<BlockedNumberResponse>() {
                @Override
                public void success(BlockedNumberResponse blockedNumberResponse, Response response) {
                    TableBlockedNumber tableBlockedNumber = new TableBlockedNumber();
                    String[] fields = { TableBlockedNumber.FIELD_BLOCKED_NUMBER };
                    BlockedNumberData data = tableBlockedNumber.find(fields, number);
                    tableBlockedNumber.delete(data);
                    if (callback != null) {
                        callback.onSuccess(null);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (callback != null) {
                        callback.onError(new BlockedNumberRemoveException(error.getResponse().getReason()));
                    }
                }
            });
        } else {
            callback.onError(new NetworkException(MyApplication.getContext().getString(R.string.no_network_connection)));
        }

    }

}
