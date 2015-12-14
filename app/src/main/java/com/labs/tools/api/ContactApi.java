package com.labs.tools.api;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.labs.tools.MyApplication;
import com.labs.tools.R;
import com.labs.tools.callback.Callback;
import com.labs.tools.database.data.ContactData;
import com.labs.tools.database.table.TableContact;
import com.labs.tools.net.RetrofitHelper;
import com.labs.tools.throwable.ContactReadException;
import com.labs.tools.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    public void readAllContact(boolean refresh, final Callback<List<ContactData>> callback) {
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                TableContact tableContact = new TableContact();
                Cursor contactCursor = mContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                List<ContactData> listContactData = new ArrayList<>();
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
            }
        };

        new Thread(runable).start();

    }

    public void syncContact(Callback<Integer> callback) {

    }

    public int getCountUnsynchronizedList() {
        return 0;
    }



}
