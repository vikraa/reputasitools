package com.labs.tools.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.labs.tools.MyApplication;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.SmsData;
import com.labs.tools.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vikraa on 11/29/2015.
 */
public class TableSms extends BaseTable<SmsData> {
    public static final String TABLE_NAME = "messages";
    private final String FIELD_SMS_SENDER = "sender";
    private final String FIELD_SMS_CONTENT = "content";
    private final String FIELD_SMS_BLOCK_STATUS = "blockedstatus";
    private final String FIELD_SMS_RECEIVED_TIMESTAMP = "receivedtimestamp";

    public static final int SMS_STATUS_BLOCKED = 1;
    public static final int SMS_STATUS_ALLOWED = 0;

    @Override
    public void onCreateTable(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "create table ( " + TABLE_NAME + " " +
                FIELD_ID + " TEXT NOT NULL PRIMARY KEY," +
                FIELD_SMS_SENDER + " TEXT," +
                FIELD_SMS_CONTENT + " TEXT," +
                FIELD_SMS_BLOCK_STATUS + " TEXT," +
                FIELD_SMS_RECEIVED_TIMESTAMP + " BIGINT," +
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
    public void deleteTable(SQLiteDatabase db) {
        db.execSQL(SQL_DROP_TABLE + TABLE_NAME);
    }

    @Override
    public void insert(SmsData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, UUID.randomUUID().toString());
        contentValues.put(FIELD_SMS_SENDER, data.getSender());
        contentValues.put(FIELD_SMS_CONTENT, data.getMessage());
        contentValues.put(FIELD_SMS_BLOCK_STATUS, data.getBlockedStatus());
        contentValues.put(FIELD_SMS_RECEIVED_TIMESTAMP, data.getReceivedTimestamp());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().insert(DataProvider.SMS_URI,contentValues);
    }

    @Override
    public void update(SmsData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, UUID.randomUUID().toString());
        contentValues.put(FIELD_SMS_SENDER, data.getSender());
        contentValues.put(FIELD_SMS_CONTENT, data.getMessage());
        contentValues.put(FIELD_SMS_BLOCK_STATUS, data.getBlockedStatus());
        contentValues.put(FIELD_SMS_RECEIVED_TIMESTAMP, data.getReceivedTimestamp());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().update(DataProvider.SMS_URI, contentValues, FIELD_ID + " = ? ", new String[]{data.getId()});
    }

    @Override
    public void delete(SmsData data) {
        MyApplication.getContext().getContentResolver().delete(DataProvider.SMS_URI, FIELD_ID + " = ?", new String[] { data.getId() });
    }

    @Override
    public void insert(List<SmsData> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            ContentValues[] cvList = new ContentValues[dataList.size()];
            int count = 0;
            for (SmsData data : dataList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FIELD_ID, UUID.randomUUID().toString());
                contentValues.put(FIELD_SMS_SENDER, data.getSender());
                contentValues.put(FIELD_SMS_CONTENT, data.getMessage());
                contentValues.put(FIELD_SMS_BLOCK_STATUS, data.getBlockedStatus());
                contentValues.put(FIELD_SMS_RECEIVED_TIMESTAMP, data.getReceivedTimestamp());
                contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
                contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
                cvList[count] = contentValues;
                count++;
            }
            MyApplication.getContext().getContentResolver().bulkInsert(DataProvider.SMS_URI,cvList);
        }
    }

    @Override
    public List<SmsData> getAll() {
        List<SmsData> queryResult = new ArrayList<>();
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.SMS_URI, null, null, null, null);
        if (qCursor != null && qCursor.moveToFirst()) {
            do {
                SmsData smsdata = new SmsData();
                smsdata.setId(qCursor.getString(qCursor.getColumnIndex(FIELD_ID)));
                smsdata.setSender(qCursor.getString(qCursor.getColumnIndex(FIELD_SMS_SENDER)));
                smsdata.setMessage(qCursor.getString(qCursor.getColumnIndex(FIELD_SMS_CONTENT)));
                smsdata.setBlockedStatus(qCursor.getInt(qCursor.getColumnIndex(FIELD_SMS_BLOCK_STATUS)));
                smsdata.setLastUpdated(qCursor.getLong(qCursor.getColumnIndex(FIELD_LAST_UPDATED_TIMESTAMP)));
                smsdata.setSynchronizedStatus(qCursor.getInt(qCursor.getColumnIndex(FIELD_SYNCHRONIZED_STATUS)));
                queryResult.add(smsdata);
            } while(qCursor.moveToNext());
        }
        return queryResult;
    }

    @Override
    public int getCount() {
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.SMS_URI, null, null, null, null);
        int counter = 0;
        if (qCursor.moveToFirst()) {
            counter = qCursor.getCount();
        }
        return counter;
    }

    @Override
    public SmsData find(String[] fields,String... keys) {
        return null;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
