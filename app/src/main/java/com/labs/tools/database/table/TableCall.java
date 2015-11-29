package com.labs.tools.database.table;

import android.database.sqlite.SQLiteDatabase;

import com.labs.tools.database.data.CallData;

import java.util.List;

/**
 * Created by vikraa on 11/29/2015.
 */
public class TableCall extends BaseTable<CallData> {

    public static final int CALL_TYPE_ANSWERED = 0;
    public static final int CALL_TYPE_REJECTED = 1;
    public static final int CALL_TYPE_MISSED = 2;

    @Override
    public void onCreateTable(SQLiteDatabase db) {

    }

    @Override
    public void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void clearTableContents(SQLiteDatabase db) {

    }

    @Override
    public void deleteTable(SQLiteDatabase db) {

    }

    @Override
    public void insert(CallData data) {

    }

    @Override
    public void update(CallData data) {

    }

    @Override
    public void delete(CallData data) {

    }

    @Override
    public void insert(List<CallData> dataList) {

    }

    @Override
    public List<CallData> getAll() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CallData find(String... keys) {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }
}
