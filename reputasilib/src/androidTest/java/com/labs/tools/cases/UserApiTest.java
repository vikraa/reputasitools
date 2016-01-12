package com.labs.tools.cases;

import android.test.AndroidTestCase;
import android.util.Log;

import com.labs.tools.api.UserApi;
import com.labs.tools.callback.Callback;
import com.labs.tools.throwable.NetworkException;
import com.labs.tools.throwable.UserAlreadyRegisteredException;
import com.labs.tools.throwable.UserDisclaimerException;
import com.labs.tools.throwable.UserLoginException;
import com.labs.tools.throwable.UserRegisterException;

/**
 * Created by Vikraa on 1/12/2016.
 */
public class UserApiTest extends AndroidTestCase {

    private UserApi mUserApi;

    @Override
    protected void setUp() throws Exception {
        mUserApi = new UserApi(getContext());
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRegister() {
        Log.d("reputasi", "starting test");
        mUserApi.register(new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Log.d("reputasi", "registration completed");
                assertTrue(result != null);
                assertTrue(mUserApi.isRegistered());
            }

            @Override
            public void onError(Throwable errorResult) {
                Log.d("reputasi", "registration failed");
                assertTrue(errorResult instanceof UserRegisterException || errorResult instanceof UserAlreadyRegisteredException || errorResult instanceof NetworkException);
                assertTrue(!mUserApi.isRegistered());
            }
        });
        Log.d("reputasi", "ending test");
    }

    public void testLogin() {
        mUserApi.login(new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                assertTrue(result == null);
                assertTrue(mUserApi.isLoggedIn());
            }

            @Override
            public void onError(Throwable errorResult) {
                assertTrue(errorResult instanceof UserLoginException || errorResult instanceof NetworkException);
                assertTrue(!mUserApi.isLoggedIn());
            }
        });
    }

    public void testDisclaimerAgreement() {
        mUserApi.confirmDisclaimerAgreement(false, new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                assertTrue(result == null);
                assertTrue(mUserApi.isDisclaimerConfirmed());
            }

            @Override
            public void onError(Throwable errorResult) {
                assertTrue(errorResult instanceof UserDisclaimerException);
                assertTrue(!mUserApi.isDisclaimerConfirmed());
            }
        });
    }
}
