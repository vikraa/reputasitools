package com.labs.tools.net;

/**
 * Created by vikraa on 12/5/2015.
 */
public class RestConstant {

    /* keys */
    public static final String APPLICATION_ID = "k6iID9uMnzhs0qpmzB5gKq2kHdKG7VNFXmv3dpN4";
    public static final String CLIENT_ID = "TWFz2UtJMwMH4WdqAwJViz28F7m0LxwosQ9YvorF";
    public static final String REST_ID = "rWxLrTXwZT9SH8rd2vrktbs9tEo0k5U6Vo3KDSnq";

    /* server end point */
    public static final String SERVER_END_POINT = "https://api.parse.com";

    /* api settings */
    public static final String HEADER_CONTENT_TYPE_JSON = "Content-Type: application/json";
    public static final String HEADER_X_PARSE_APPLICATION_ID = "X-Parse-Application-Id";
    public static final String HEADER_X_PARSE_REST_API_ID = "X-Parse-REST-API-Key";
    public static final String HEADER_X_PARSE_SESSIONTOKEN = "X-Parse-Session-Token";

    /* api url */
    public static final String API_REGISTRATION = "/1/users";
    public static final String API_LOGIN = "/1/login";
    public static final String API_GET_BLOCKED_NUMBER = "/1/functions/getUserSpecialNumber";;
    public static final String API_ADD_BLOCKED_NUMBER = "/1/functions/addUserSpecialNumber";
    public static final String API_REMOVE_BLOCKED_NUMBER = "/1/functions/deleteUserSpecialNumber";
    public static final String API_CONTRIBUTE_NUMBER = "/1/functions/contributeNumber";
    public static final String API_SEARCH_NUMBER = "/1/functions/searchNumber";
    public static final String API_SYNC_CONTACT = "/1/functions/sendUserContact";
    public static final String API_CHECK_CONTACT_HASH = "/1/functions/checkContactHash";

    /* response status */
    public static final int RESPONSE_USER_ALREADY_REGISTERED = 202;
    public static final int RESPONSE_USER_CREATED = 201;

}
