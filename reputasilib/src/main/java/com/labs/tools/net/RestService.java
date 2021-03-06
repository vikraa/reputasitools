package com.labs.tools.net;

import com.labs.tools.net.request.ContactRequest;
import com.labs.tools.net.request.ContributeRequest;
import com.labs.tools.net.request.SearchRequest;
import com.labs.tools.net.response.BlockedNumberResponse;
import com.labs.tools.net.response.ContactHashResponse;
import com.labs.tools.net.response.ContactResponse;
import com.labs.tools.net.response.ContributeResponse;
import com.labs.tools.net.response.LoginResponse;
import com.labs.tools.net.response.RegistrationResponse;
import com.labs.tools.net.request.BlockedNumberRequest;
import com.labs.tools.net.request.LoginRequest;
import com.labs.tools.net.request.RegistrationRequest;
import com.labs.tools.net.response.SearchResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedInput;

public interface RestService {

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_REGISTRATION)
    void userRegister(@Body RegistrationRequest request, Callback<RegistrationResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @GET(RestConstant.API_LOGIN)
    void userLogin(@Query("username") String username, @Query("password") String password, Callback<LoginResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_GET_BLOCKED_NUMBER)
    void blockedNumberList(@Body BlockedNumberRequest request, Callback<BlockedNumberResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_ADD_BLOCKED_NUMBER)
    void addBlockedNumber(@Body BlockedNumberRequest request, Callback<BlockedNumberResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_REMOVE_BLOCKED_NUMBER)
    void removeBlockedNumber(@Body BlockedNumberRequest request, Callback<BlockedNumberResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_CONTRIBUTE_NUMBER)
    void contributeNumber(@Body ContributeRequest request, Callback<ContributeResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_SEARCH_NUMBER)
    void searchNumber(@Body SearchRequest request, Callback<SearchResponse> callback);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_SYNC_CONTACT)
    ContactResponse syncContact(@Body ContactRequest request);

    @Headers(RestConstant.HEADER_CONTENT_TYPE_JSON)
    @POST(RestConstant.API_CHECK_CONTACT_HASH)
    void checkContactHash(@Body TypedInput hash, Callback<ContactHashResponse> callback);

}