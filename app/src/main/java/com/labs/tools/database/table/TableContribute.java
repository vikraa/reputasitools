package com.labs.tools.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.labs.tools.MyApplication;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.ContributeData;
import com.labs.tools.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vikraa on 12/1/2015.
 */
public class TableContribute extends BaseTable<ContributeData> {
    public static final String TABLE_NAME = "contribute";
    private static String FIELD_CONTRIBUTE_NAME = "name";
    private static String FIELD_CONTRIBUTE_NUMBER = "number";
    private static String FIELD_CONTRIBUTE_NOTES = "notes";

    @Override
    public void onCreateTable(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "create table ( " + TABLE_NAME + " " +
                FIELD_ID + " TEXT NOT NULL PRIMARY KEY," +
                FIELD_CONTRIBUTE_NAME + " TEXT," +
                FIELD_CONTRIBUTE_NUMBER + " TEXT," +
                FIELD_CONTRIBUTE_NOTES + " TEXT," +
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
    public void insert(ContributeData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, UUID.randomUUID().toString());
        contentValues.put(FIELD_CONTRIBUTE_NAME, data.getName());
        contentValues.put(FIELD_CONTRIBUTE_NUMBER, data.getNumber());
        contentValues.put(FIELD_CONTRIBUTE_NOTES, data.getNotes());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().insert(DataProvider.CONTRIBUTE_URI, contentValues);
    }

    @Override
    public void update(ContributeData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, data.getId());
        contentValues.put(FIELD_CONTRIBUTE_NAME, data.getName());
        contentValues.put(FIELD_CONTRIBUTE_NUMBER, data.getNumber());
        contentValues.put(FIELD_CONTRIBUTE_NOTES, data.getNotes());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, TimeUtils.getCurrentTimestamp());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().update(DataProvider.CONTRIBUTE_URI, contentValues, FIELD_ID + " = ?", new String[] { data.getId() });
    }

    @Override
    public void delete(ContributeData data) {
        MyApplication.getContext().getContentResolver().delete(DataProvider.CONTRIBUTE_URI, FIELD_ID + " = ?", new String[] { data.getId() });
    }

    @Override
    public void insert(List<ContributeData> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            ContentValues[] cvList = new ContentValues[dataList.size()];
            int count = 0;
            for (ContributeData data : dataList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FIELD_ID, UUID.randomUUID().toString());
                contentValues.put(FIELD_CONTRIBUTE_NAME, data.getName());
                contentValues.put(FIELD_CONTRIBUTE_NUMBER, data.getNumber());
                contentValues.put(FIELD_CONTRIBUTE_NOTES, data.getNotes());
                contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, data.getLastUpdated());
                contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
                cvList[count] = contentValues;
                count++;
            }
            MyApplication.getContext().getContentResolver().bulkInsert(DataProvider.CONTRIBUTE_URI, cvList);
        }
    }

    @Override
    public List<ContributeData> getAll() {
        List<ContributeData> queryResult = new ArrayList<>();
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.CONTRIBUTE_URI, null, null, null, null);
        if (qCursor != null && !qCursor.moveToFirst()) {
            do {
                ContributeData contributedata = new ContributeData();
                contributedata.setId(qCursor.getString(qCursor.getColumnIndex(FIELD_ID)));
                contributedata.setName(qCursor.getString(qCursor.getColumnIndex(FIELD_CONTRIBUTE_NAME)));
                contributedata.setNumber(qCursor.getString(qCursor.getColumnIndex(FIELD_CONTRIBUTE_NUMBER)));
                contributedata.setNotes(qCursor.getString(qCursor.getColumnIndex(FIELD_LAST_UPDATED_TIMESTAMP)));
                contributedata.setSynchronizedStatus(qCursor.getInt(qCursor.getColumnIndex(FIELD_SYNCHRONIZED_STATUS)));
                queryResult.add(contributedata);
            } while(qCursor.moveToNext());
        }
        return queryResult;
    }

    @Override
    public int getCount() {
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.CONTRIBUTE_URI, null, null, null, null);
        int counter = 0;
        if (qCursor.moveToFirst()) {
            counter = qCursor.getCount();
        }
        return counter;
    }

    @Override
    public ContributeData find(String[] fields,String... keys) {
        return null;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
