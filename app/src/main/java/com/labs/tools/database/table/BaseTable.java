package com.labs.tools.database.table;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Vikraa on 11/26/2015.
 */
public abstract class BaseTable<T> {
    public static final int STATUS_SYNCHRONIZED_SUCCESS = 1;
    public static final int STATUS_SYNCHRONIZED_FAILED = 0;
    protected String FIELD_ID = "rowid";
    protected String FIELD_LAST_UPDATED_TIMESTAMP = "lastupdated";
    protected String FIELD_SYNCHRONIZED_STATUS = "synchronizedstatus";
    protected String SQL_DROP_TABLE = "drop table ";
    protected String SQL_DELETE_ALL_CONTENTS = "delete from ";
    public abstract void onCreateTable(SQLiteDatabase db);
    public abstract void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion);
    public abstract void clearTableContents(SQLiteDatabase db);
    public abstract void deleteTable(SQLiteDatabase db);
    public abstract void insert(T data);
    public abstract void update(T data);
    public abstract void delete(T data);
    public abstract void insert(List<T> dataList);
    public abstract List<T> getAll();
    public abstract int getCount();
    public abstract T find(String...keys);
    public abstract String getTableName();
}
