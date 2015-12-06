package com.labs.tools.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.labs.tools.MyApplication;
import com.labs.tools.database.DataProvider;
import com.labs.tools.database.data.BlockedNumberData;
import com.labs.tools.database.data.ContributeData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vikraa on 12/5/2015.
 */
public class TableBlockedNumber extends BaseTable<BlockedNumberData> {
    public static final String TABLE_NAME = "blockednumber";
    public static final String FIELD_BLOCKED_NAME = "name";
    public static final String FIELD_BLOCKED_NUMBER = "number";
    public static final String FIELD_BLOCKED_CATEGORY_ID = "categoryid";

    @Override
    public void onCreateTable(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "create table ( " + TABLE_NAME +
                FIELD_ID + " TEXT PRIMARY KEY NOT NULL," +
                FIELD_BLOCKED_NAME + " TEXT," +
                FIELD_BLOCKED_NUMBER + " TEXT," +
                FIELD_BLOCKED_CATEGORY_ID + " TEXT," +
                FIELD_LAST_UPDATED_TIMESTAMP + " BIGINT," +
                FIELD_SYNCHRONIZED_STATUS + " BIGINT )";
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
    public void insert(BlockedNumberData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, UUID.randomUUID().toString());
        contentValues.put(FIELD_BLOCKED_NAME, data.getName());
        contentValues.put(FIELD_BLOCKED_NUMBER, data.getNumber());
        contentValues.put(FIELD_BLOCKED_CATEGORY_ID, data.getCategoryId());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, data.getLastUpdated());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().insert(DataProvider.BLOCKEDNUMBER_URI,contentValues);
    }

    @Override
    public void update(BlockedNumberData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, UUID.randomUUID().toString());
        contentValues.put(FIELD_BLOCKED_NAME, data.getName());
        contentValues.put(FIELD_BLOCKED_NUMBER, data.getNumber());
        contentValues.put(FIELD_BLOCKED_CATEGORY_ID, data.getCategoryId());
        contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, data.getLastUpdated());
        contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
        MyApplication.getContext().getContentResolver().update(DataProvider.BLOCKEDNUMBER_URI,contentValues, FIELD_ID + " = ?", new String[] { data.getId() });
    }

    @Override
    public void delete(BlockedNumberData data) {
        MyApplication.getContext().getContentResolver().delete(DataProvider.BLOCKEDNUMBER_URI, FIELD_ID + " = ?", new String[] { data.getId() });
    }

    @Override
    public void insert(List<BlockedNumberData> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            ContentValues[] cvList = new ContentValues[dataList.size()];
            int count = 0;
            for (BlockedNumberData data : dataList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FIELD_ID, UUID.randomUUID().toString());
                contentValues.put(FIELD_BLOCKED_NAME, data.getName());
                contentValues.put(FIELD_BLOCKED_NUMBER, data.getNumber());
                contentValues.put(FIELD_BLOCKED_CATEGORY_ID, data.getCategoryId());
                contentValues.put(FIELD_LAST_UPDATED_TIMESTAMP, data.getLastUpdated());
                contentValues.put(FIELD_SYNCHRONIZED_STATUS, data.getSynchronizedStatus());
                cvList[count] = contentValues;
                count++;
            }
            MyApplication.getContext().getContentResolver().bulkInsert(DataProvider.BLOCKEDNUMBER_URI, cvList);
        }
    }

    @Override
    public List<BlockedNumberData> getAll() {
        List<BlockedNumberData> queryResult = new ArrayList<>();
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.BLOCKEDNUMBER_URI, null, null, null, null);
        if (qCursor != null && !qCursor.moveToFirst()) {
            do {
                BlockedNumberData blockedNumberData = new BlockedNumberData();
                blockedNumberData.setId(qCursor.getString(qCursor.getColumnIndex(FIELD_ID)));
                blockedNumberData.setName(qCursor.getString(qCursor.getColumnIndex(FIELD_BLOCKED_NAME)));
                blockedNumberData.setNumber(qCursor.getString(qCursor.getColumnIndex(FIELD_BLOCKED_NUMBER)));
                blockedNumberData.setCategoryId(qCursor.getString(qCursor.getColumnIndex(FIELD_BLOCKED_CATEGORY_ID)));
                blockedNumberData.setLastUpdated(qCursor.getLong(qCursor.getColumnIndex(FIELD_LAST_UPDATED_TIMESTAMP)));
                blockedNumberData.setSynchronizedStatus(qCursor.getInt(qCursor.getColumnIndex(FIELD_SYNCHRONIZED_STATUS)));
                queryResult.add(blockedNumberData);
            } while(qCursor.moveToNext());
        }
        return queryResult;
    }

    @Override
    public int getCount() {
        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.BLOCKEDNUMBER_URI, null, null, null, null);
        int counter = 0;
        if (qCursor.moveToFirst()) {
            counter = qCursor.getCount();
        }
        return counter;
    }

    @Override
    public BlockedNumberData find(String[] fields, String... keys) {
        String sql = "";
        for (String st : fields) {
            sql += fields + " = ? AND ";
        }
        sql = sql.substring(0, sql.length() - 6);

        Cursor qCursor = MyApplication.getContext().getContentResolver().query(DataProvider.BLOCKEDNUMBER_URI, null, sql, keys, null);
        BlockedNumberData result = null;
        if (qCursor != null && qCursor.moveToFirst()) {
            result = new BlockedNumberData();
            result.setId(qCursor.getString(qCursor.getColumnIndex(FIELD_ID)));
            result.setName(qCursor.getString(qCursor.getColumnIndex(FIELD_BLOCKED_NAME)));
            result.setNumber(qCursor.getString(qCursor.getColumnIndex(FIELD_BLOCKED_NUMBER)));
            result.setCategoryId(qCursor.getString(qCursor.getColumnIndex(FIELD_BLOCKED_CATEGORY_ID)));
            result.setSynchronizedStatus(qCursor.getInt(qCursor.getColumnIndex(FIELD_SYNCHRONIZED_STATUS)));
            result.setLastUpdated(qCursor.getLong(qCursor.getColumnIndex(FIELD_LAST_UPDATED_TIMESTAMP)));
        }
        return result;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
