package com.tester.labs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.labs.tools.api.ContactApi;
import com.labs.tools.api.UserApi;
import com.labs.tools.callback.Callback;
import com.labs.tools.database.data.ContactData;
import com.labs.tools.model.ContactModel;
import com.labs.tools.model.ContactSyncResultModel;

import java.util.List;


public class MainActivity extends Activity {
    private UserApi mUserApi;
    private ContactApi mContactApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testUserApi(AppSample.getContext());
            }
        });
        Button btn2 = (Button)findViewById(R.id.btn_check);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "testing", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void testUserApi(final Context context) {
        mUserApi = new UserApi(context);
        mUserApi.register(new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Log.d("userapi", "success register");
                testContactApi(context);
            }

            @Override
            public void onError(Throwable errorResult) {
                Log.d("userapi", "failed register");
            }
        });
    }


    public void testContactApi(final Context context) {
        mContactApi = new ContactApi(context);
        if (mUserApi.isLoggedIn()) {
            mContactApi.readAllContact(true, new Callback<ContactModel>() {
                @Override
                public void onSuccess(ContactModel result) {
                    List<ContactData> dataList = result.getContactList();
                    String hash = result.getHash();
                    testSyncContactApi(context);
                    Log.d("contactapi", "readcontact done -- list size " + dataList.size() + " -- hash " + hash);
                }

                @Override
                public void onError(Throwable errorResult) {
                    Log.d("contactapi", "readcontact failed");
                }
            });
        }
    }

    public void testSyncContactApi(Context context) {
        mContactApi.syncContactHash(new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                Log.d("contactapi", "synccontact hash - " + result);
                if (result) {
                    mContactApi.syncContact(new Callback<ContactSyncResultModel>() {
                        @Override
                        public void onSuccess(ContactSyncResultModel result) {
                            Log.d("contactapi", "synccontact result ok");
                        }

                        @Override
                        public void onError(Throwable errorResult) {
                            Log.d("contactapi", "synccontact result failed");
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable errorResult) {
                Log.d("contactapi", "synccontact hash failed");
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
