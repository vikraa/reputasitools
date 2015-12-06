package com.labs.tools.net;

import com.labs.tools.net.Response.BlockedNumberResponse;
import com.labs.tools.net.Response.LoginResponse;
import com.labs.tools.net.Response.RegistrationResponse;
import com.labs.tools.net.request.BlockedNumberRequest;
import com.labs.tools.net.request.LoginRequest;
import com.labs.tools.net.request.RegistrationRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface RestService {

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_REGISTRATION)
    void userRegister(@Body RegistrationRequest request, Callback<RegistrationResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_LOGIN)
    void userLogin(@Body LoginRequest request, Callback<LoginResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_GET_BLOCKED_NUMBER)
    void blockedNumberList(@Body BlockedNumberRequest request, Callback<BlockedNumberResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_ADD_BLOCKED_NUMBER)
    void addBlockedNumber(@Body BlockedNumberRequest request, Callback<BlockedNumberResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_REMOVE_BLOCKED_NUMBER)
    void removeBlockedNumber(@Body BlockedNumberRequest request, Callback<BlockedNumberResponse> callback);
}