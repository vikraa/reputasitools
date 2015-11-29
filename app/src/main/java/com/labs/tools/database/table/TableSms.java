package com.labs.tools.database.table;

import android.database.sqlite.SQLiteDatabase;

import com.labs.tools.database.data.SmsData;

import java.util.List;

/**
 * Created by vikraa on 11/29/2015.
 */
public class TableSms extends BaseTable<SmsData> {

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
    public void insert(SmsData data) {

    }

    @Override
    public void update(SmsData data) {

    }

    @Override
    public void delete(SmsData data) {

    }

    @Override
    public void insert(List<SmsData> dataList) {

    }

    @Override
    public List<SmsData> getAll() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public SmsData find(String... keys) {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }
}
