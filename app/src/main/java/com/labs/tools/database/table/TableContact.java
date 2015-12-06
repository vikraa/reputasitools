package com.labs.tools.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;

import com.labs.tools.MyApplication;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.ContactData;
import com.labs.tools.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Vikraa on 11/26/2015.
 */
public class TableContact extends BaseTable<ContactData> {

    public static final String TABLE_NAME = "contact";
    private final String FIELD_CONTACT_NAME = "name";
    private final String FIELD_CONTACT_NUMBER = "number";
    private final String FIELD_CONTACT_EMAIL = "email";

    @Override
    public void onCreateTable(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "create table ( " + TABLE_NAME + " " +
                FIELD_ID + " TEXT NOT NULL PRIMARY KEY," +
                FIELD_CONTACT_NAME + " TEXT NOT NULL," +
                FIELD_CONTACT_NUMBER + " TEXT," +
                FIELD_CONTACT_EMAIL + " TEXT," +
                FIELD_LAST_UPDATED_TIMESTAMP + " BIGINT, " +
                FIELD_SYNCHRONIZED_STATUS + " BIGINT DEFAULT 0 )";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void clearTableContents(SQLiteDatabase db) {
       db.execSQL(SQL_DELETE_ALL_CONTENTS + TABLE_NAME);
    }

    @Override
    public void insert(ContactData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, UUID.randomUUID().toString());
        contentValues.put(FIELD_CONTACT_NAME, data.getName());
        contentValues.put(FIELD_CONTACT_NUMBER, data.getNumber());
        contentValues.put(FIELD_CONTACT_EMAIL, data.getEmail());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().insert(DataProvider.CONTACT_URI, contentValues);
    }

    @Override
    public void update(ContactData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, data.getId());
        contentValues.put(FIELD_CONTACT_NAME, data.getName());
        contentValues.put(FIELD_CONTACT_NUMBER, data.getNumber());
        contentValues.put(FIELD_CONTACT_EMAIL, data.getEmail());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().update(DataProvider.CONTACT_URI, contentValues, FIELD_ID + " = ?", new String[]{data.getId()});
    }

    @Override
    public void delete(ContactData data) {
        MyApplication.getContext().getContentResolver().delete(DataProvider.CONTACT_URI,FIELD_ID + " = ?", new String[] { data.getId() });
    }

    @Override
    public void insert(List<ContactData> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            ContentValues[] cvList = new ContentValues[dataList.size()];
            int count = 0;
            for (ContactData data : dataList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FIELD_ID, UUID.randomUUID().toString());
                contentValues.put(FIELD_CONTACT_NAME, data.getName());
                contentValues.put(FIELD_CONTACT_NUMBER, data.getNumber());
                contentValues.put(FIELD_CONTACT_EMAIL, data.getEmail());
                contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
                contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
                cvList[count] = contentValues;
                count++;
            }
            MyApplication.getContext().getContentResolver().bulkInsert(DataProvider.CONTACT_URI, cvList);
        }
    }

    @Override
    public List<ContactData> getAll() {
        List<ContactData> queryResult = new ArrayList<>();
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.CONTACT_URI, null, null, null, null);
        if (qCursor != null && qCursor.moveToFirst()) {
            do {
                ContactData contact = new ContactData();
                contact.setId(qCursor.getString(qCursor.getColumnIndex(FIELD_ID)));
                contact.setName(qCursor.getString(qCursor.getColumnIndex(FIELD_CONTACT_NAME)));
                contact.setNumber(qCursor.getString(qCursor.getColumnIndex(FIELD_CONTACT_NUMBER)));
                contact.setEmail(qCursor.getString(qCursor.getColumnIndex(FIELD_CONTACT_EMAIL)));
                contact.setLastUpdated(qCursor.getLong(qCursor.getColumnIndex(FIELD_LAST_UPDATED_TIMESTAMP)));
                contact.setSynchronizedStatus(qCursor.getInt(qCursor.getColumnIndex(FIELD_SYNCHRONIZED_STATUS)));
                queryResult.add(contact);
            } while(qCursor.moveToNext());
        }
        return queryResult;
    }

    @Override
    public int getCount() {
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.CONTACT_URI, null, null, null, null);
        int counter = 0;
        if (qCursor.moveToFirst()) {
            counter = qCursor.getCount();
        }
        return counter;
    }

    @Override
    public ContactData find(String[] fields,String... keys) {
        return null;
    }

    @Override
    public void deleteTable(SQLiteDatabase db) {
        db.execSQL(SQL_DROP_TABLE + TABLE_NAME);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
