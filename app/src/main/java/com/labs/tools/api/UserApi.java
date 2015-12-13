package com.labs.tools.api;

import android.content.Context;
import android.text.TextUtils;

import com.labs.tools.MyApplication;
import com.labs.tools.R;
import com.labs.tools.callback.Callback;
import com.labs.tools.net.response1.LoginResponse;
import com.labs.tools.net.response1.RegistrationResponse;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.LoginRequest;
import com.labs.tools.net.request.RegistrationRequest;
import com.labs.tools.throwable.NetworkException;
import com.labs.tools.throwable.UserAlreadyRegisteredException;
import com.labs.tools.throwable.UserDisclaimerException;
import com.labs.tools.throwable.UserLoginException;
import com.labs.tools.util.AppUtils;
import com.labs.tools.util.DeviceUtils;
import com.labs.tools.util.NetworkUtils;

import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vikraa on 12/5/2015.
 */
public class UserApi extends BaseApi<RegistrationRequest, Callback<RegistrationResponse>> {
    private Context mContext;
    private RetrofitHelper mRetrofit;

    public UserApi(Context context) {
        mContext = context;
        mRetrofit = new RetrofitHelper();
    }

    public void register(final Callback<Void> callback) {
        if (NetworkUtils.isNetworkConnected(mContext)) {
            RegistrationRequest registrationRequest = new RegistrationRequest();
            String imei = DeviceUtils.getImei(mContext);
            registrationRequest.setUserName(imei);
            registrationRequest.setPassword(AppUtils.generateMd5(imei));
            registrationRequest.setDeviceInfo(DeviceUtils.getDeviceInfo());
            registrationRequest.setAuthData(null);
            registrationRequest.setGender("");
            registrationRequest.setDisplayName(imei);

            mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                }
            }, MyApplication.getLogLevel()).userRegister(registrationRequest, new retrofit.Callback<RegistrationResponse>() {
                @Override
                public void success(RegistrationResponse registrationResponse, Response response) {
                    Preferences.getInstance(mContext).putSession(registrationResponse.getSessionToken());
                    callback.onSuccess(null);
                }

                @Override
                public void failure(RetrofitError error) {
                    callback.onError(new UserAlreadyRegisteredException(error));
                }
            });
        } else {
            callback.onError(new NetworkException(MyApplication.getContext().getString(R.string.no_network_connection)));
        }
    }

    public void login(final Callback<Void> callback) {
        if (NetworkUtils.isNetworkConnected(mContext)) {
            LoginRequest loginRequest = new LoginRequest();
            String imei = DeviceUtils.getImei(mContext);
            loginRequest.setUserName(imei);
            loginRequest.setPassword(AppUtils.generateMd5(imei));
            mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                    request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                }
            }, MyApplication.getLogLevel()).userLogin(loginRequest, new retrofit.Callback<LoginResponse>() {
                @Override
                public void success(LoginResponse loginResponse, Response response) {
                    Preferences.getInstance(mContext).putSession(loginResponse.getSessionToken());
                    callback.onSuccess(null);
                }

                @Override
                public void failure(RetrofitError error) {
                    callback.onError(new UserLoginException(error));
                }
            });
        } else {
            callback.onError(new NetworkException(MyApplication.getContext().getString(R.string.no_network_connection)));
        }
    }

    public void confirmDisclaimerAgreement(boolean agree, Callback<Void> callback) {
        Preferences.getInstance(mContext).setAgreeDisclaimer(agree);
        if (agree) {
           callback.onSuccess(null);
        } else {
            callback.onError(new UserDisclaimerException("user decline agreement"));
        }
    }

    public boolean isDisclaimerConfirmed() {
        return Preferences.getInstance(mContext).isAgreeDisclaimer();
    }

    public boolean isLoggedIn() {
        return TextUtils.isEmpty(Preferences.getInstance(mContext).getSessionToken()) ? false : true;
    }

    public boolean isRegistered() {
        return TextUtils.isEmpty(Preferences.getInstance(mContext).getSessionToken()) ? false : true;
    }

}
