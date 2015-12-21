package com.labs.tools.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.labs.tools.MyApplication;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.CallData;
import com.labs.tools.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vikraa on 11/29/2015.
 */
public class TableCall extends BaseTable<CallData> {

    public static final String TABLE_NAME = "contact";
    public static String FIELD_CALL_FROM_NAME = "fromname";
    public static String FIELD_CALL_NUMBER = "number";
    public static String FIELD_CALL_TYPE = "type";

    public static final int CALL_TYPE_ANSWERED = 0;
    public static final int CALL_TYPE_REJECTED = 1;
    public static final int CALL_TYPE_MISSED = 2;

    @Override
    public void onCreateTable(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + "( " +
                FIELD_ID + " TEXT NOT NULL PRIMARY KEY," +
                FIELD_CALL_FROM_NAME + " TEXT," +
                FIELD_CALL_NUMBER + " TEXT," +
                FIELD_CALL_TYPE + " INTEGER," +
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
    public void insert(CallData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, UUID.randomUUID().toString());
        contentValues.put(FIELD_CALL_FROM_NAME, data.getFromName());
        contentValues.put(FIELD_CALL_NUMBER, data.getNumber());
        contentValues.put(FIELD_CALL_TYPE, data.getCallType());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().insert(DataProvider.CALL_URI, contentValues);
    }

    @Override
    public void update(CallData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, data.getId());
        contentValues.put(FIELD_CALL_FROM_NAME, data.getFromName());
        contentValues.put(FIELD_CALL_NUMBER, data.getNumber());
        contentValues.put(FIELD_CALL_TYPE, data.getCallType());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().update(DataProvider.CALL_URI, contentValues, FIELD_ID + " = ? ", new String[] { data.getId() });
    }

    @Override
    public void delete(CallData data) {
        MyApplication.getContext().getContentResolver().delete(DataProvider.CALL_URI, FIELD_ID + " = ?", new String[] { data.getId() });
    }

    @Override
    public void insert(List<CallData> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            ContentValues[] cvList = new ContentValues[dataList.size()];
            int count = 0;
            for (CallData data : dataList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FIELD_ID, UUID.randomUUID().toString());
                contentValues.put(FIELD_CALL_FROM_NAME, data.getFromName());
                contentValues.put(FIELD_CALL_NUMBER, data.getNumber());
                contentValues.put(FIELD_CALL_TYPE, data.getCallType());
                contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
                contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
                cvList[count] = contentValues;
                count++;
            }
            MyApplication.getContext().getContentResolver().bulkInsert(DataProvider.CALL_URI, cvList);
        }
    }

    @Override
    public List<CallData> getAll() {
        List<CallData> queryResult = new ArrayList<>();
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.CALL_URI, null, null, null, null);
        if (qCursor != null && qCursor.moveToFirst()) {
            do {
                CallData calldata = new CallData();
                calldata.setId(qCursor.getString(qCursor.getColumnIndex(FIELD_ID)));
                calldata.setFromName(qCursor.getString(qCursor.getColumnIndex(FIELD_CALL_FROM_NAME)));
                calldata.setNumber(qCursor.getString(qCursor.getColumnIndex(FIELD_CALL_NUMBER)));
                calldata.setCallType(qCursor.getInt(qCursor.getColumnIndex(FIELD_CALL_TYPE)));
                calldata.setLastUpdated(qCursor.getLong(qCursor.getColumnIndex(FIELD_LAST_UPDATED_TIMESTAMP)));
                calldata.setSynchronizedStatus(qCursor.getInt(qCursor.getColumnIndex(FIELD_SYNCHRONIZED_STATUS)));
                queryResult.add(calldata);
            } while (qCursor.moveToNext());
        }
        qCursor.close();
        return queryResult;
    }

    @Override
    public int getCount() {
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.CALL_URI, null, null, null, null);
        int counter = 0;
        if (qCursor.moveToFirst()) {
            counter = qCursor.getCount();
        }
        qCursor.close();
        return counter;
    }

    @Override
    public CallData find(String[] fields,String... keys) {
        return null;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
