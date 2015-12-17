package com.labs.tools.api;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.labs.tools.MyApplication;
import com.labs.tools.R;
import com.labs.tools.callback.Callback;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.ContactData;
import com.labs.tools.database.table.TableContact;
import com.labs.tools.net.RestConstant;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.net.request.ContactRequest;
import com.labs.tools.net.response.ContactResponse;
import com.labs.tools.throwable.ContactReadException;
import com.labs.tools.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit.RequestInterceptor;
import retrofit.RetrofitError;

/**
 * Created by vikraa on 12/13/2015.
 */
public class ContactApi extends BaseApi<Void, Callback<List<ContactData>>> {
    private Context mContext;
    private RetrofitHelper mRetrofit;
    private ContentResolver mContentResolver;

    public ContactApi(Context context) {
        this.mContext = context;
        mRetrofit = new RetrofitHelper();
        mContentResolver = MyApplication.getContext().getContentResolver();
    }


    public void readAllContact(final boolean refresh, final Callback<List<ContactData>> callback) {
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                List<ContactData> listContactData = new ArrayList<>();
                if(refresh) {
                    TableContact tableContact = new TableContact();
                    mContentResolver.delete(DataProvider.CONTACT_URI, null, null);
                    Cursor contactCursor = mContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                    if (contactCursor.getCount() > 0) {
                        while (contactCursor.moveToNext()) {
                            if (Integer.parseInt(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0 ) {
                                String id = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));
                                String name = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                Cursor phoneCursor = mContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);
                                while (phoneCursor.moveToNext()){
                                    ContactData contactData = new ContactData();
                                    contactData.setId(UUID.randomUUID().toString());
                                    contactData.setName(name);
                                    contactData.setNumber(phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    contactData.setEmail("");
                                    contactData.setLastUpdated(TimeUtils.getCurrentTimestamp());
                                    contactData.setSynchronizedStatus(TableContact.STATUS_SYNCHRONIZED_FAILED);
                                    listContactData.add(contactData);
                                }
                                phoneCursor.close();;
                            }
                        }
                        contactCursor.close();

                        if (listContactData.size() > 0) {
                            tableContact.insert(listContactData);
                        }

                        if (callback != null) {
                            callback.onSuccess(listContactData);
                        }
                    } else {
                        if (callback != null) {
                            callback.onError(new ContactReadException(MyApplication.getContext().getString(R.string.read_contact_empty)));
                        }
                    }
                } else {
                    Cursor contactCursor = mContentResolver.query(DataProvider.CONTACT_URI, null, null, null, null);
                    while (contactCursor.moveToNext()) {
                        ContactData contactData = new ContactData();
                        contactData.setId(contactCursor.getString(contactCursor.getColumnIndex(TableContact.FIELD_ID)));
                        contactData.setName(contactCursor.getString(contactCursor.getColumnIndex(TableContact.FIELD_CONTACT_NAME)));
                        contactData.setNumber(contactCursor.getString(contactCursor.getColumnIndex(TableContact.FIELD_CONTACT_NUMBER)));
                        contactData.setEmail(contactCursor.getString(contactCursor.getColumnIndex(TableContact.FIELD_CONTACT_EMAIL)));
                        contactData.setLastUpdated(contactCursor.getLong(contactCursor.getColumnIndex(TableContact.FIELD_LAST_UPDATED_TIMESTAMP)));
                        contactData.setSynchronizedStatus(contactCursor.getInt(contactCursor.getColumnIndex(TableContact.FIELD_SYNCHRONIZED_STATUS)));
                        listContactData.add(contactData);
                    }
                    contactCursor.close();
                    if (callback != null) {
                        callback.onSuccess(listContactData);
                    }
                }
            }
        };

        new Thread(runable).start();

    }

    public void syncContact(final Callback<Integer> callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Cursor cursor = mContentResolver.query(DataProvider.CONTACT_URI, null, TableContact.FIELD_SYNCHRONIZED_STATUS + " = ? ", new String[] { String.valueOf(TableContact.STATUS_SYNCHRONIZED_FAILED) }, null);
                if (cursor.getCount() > 0) {
                    TableContact tableContact = new TableContact();
                    while(cursor.moveToNext()) {
                        ContactData contactData = new ContactData();
                        contactData.setId(cursor.getString(cursor.getColumnIndex(TableContact.FIELD_ID)));
                        contactData.setName(cursor.getString(cursor.getColumnIndex(TableContact.FIELD_CONTACT_NAME)));
                        contactData.setNumber(cursor.getString(cursor.getColumnIndex(TableContact.FIELD_CONTACT_NUMBER)));
                        contactData.setEmail(cursor.getString(cursor.getColumnIndex(TableContact.FIELD_CONTACT_EMAIL)));
                        try {
                            ContactResponse response = mRetrofit.createRestService(RestConstant.SERVER_END_POINT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, RetrofitHelper.DEFAULT_CONNECTION_TIMEOUT, new RequestInterceptor() {
                                @Override
                                public void intercept(RequestFacade request) {
                                    request.addHeader(RestConstant.HEADER_X_PARSE_APPLICATION_ID, RestConstant.APPLICATION_ID);
                                    request.addHeader(RestConstant.HEADER_X_PARSE_REST_API_ID, RestConstant.REST_ID);
                                    request.addHeader(RestConstant.HEADER_X_PARSE_SESSIONTOKEN, Preferences.getInstance(mContext).getSessionToken());
                                }
                            }, MyApplication.getLogLevel()).syncContact(new ContactRequest(cursor.getString(cursor.getColumnIndex(TableContact.FIELD_CONTACT_NAME)),
                                    cursor.getString(cursor.getColumnIndex(TableContact.FIELD_CONTACT_NUMBER))));
                            if (response != null) {
                                contactData.setLastUpdated(TimeUtils.getCurrentTimestamp());
                                contactData.setSynchronizedStatus(TableContact.STATUS_SYNCHRONIZED_SUCCESS);
                            }
                        } catch (RetrofitError err) {
                            contactData.setLastUpdated(TimeUtils.getCurrentTimestamp());
                            contactData.setSynchronizedStatus(TableContact.STATUS_SYNCHRONIZED_FAILED);
                        } catch (Exception ex) {
                            contactData.setLastUpdated(TimeUtils.getCurrentTimestamp());
                            contactData.setSynchronizedStatus(TableContact.STATUS_SYNCHRONIZED_FAILED);
                        } finally {
                            tableContact.update(contactData);
                        }
                    }
                }
                cursor.close();
                callback.onSuccess(TableContact.STATUS_SYNCHRONIZED_TASK_FINISH);
            }
        };

        new Thread(runnable).start();
    }

    public int getCountUnsynchronizedList() {
        return 0;
    }



}