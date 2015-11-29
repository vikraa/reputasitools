package com.labs.tools.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;

import com.labs.tools.MyApplication;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.ContactData;
import com.labs.tools.util.TimeUtils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Vikraa on 11/26/2015.
 */
public class TableContact extends BaseTable<ContactData> {

    public static final String TABLE_NAME = "contact";
    private final String FIELD_ID = "id";
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
                FIELD_LAST_UPDATED_TIMESTAMP + " BIGINT )";
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
                cvList[count] = contentValues;
                count++;
            }
            MyApplication.getContext().getContentResolver().bulkInsert(DataProvider.CONTACT_URI, cvList);
        }
    }

    @Override
    public List<ContactData> getAll() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public ContactData find(String... keys) {
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
