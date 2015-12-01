package com.labs.tools.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.labs.tools.database.table.TableCall;
import com.labs.tools.database.table.TableContact;
import com.labs.tools.database.table.TableContribute;
import com.labs.tools.database.table.TableSms;

/**
 * Created by Vikraa on 11/26/2015.
 */
public class DataProvider extends ContentProvider {
    private DatabaseManager mDatabaseManager;
    private static final int CONTACT = 100;

    private static final String AUTHORITY = "com.labs.tools";
    private static final String BASE_PATH = DatabaseManager.NAME + "/";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri CONTACT_URI = Uri.withAppendedPath(CONTENT_URI, BASE_PATH + TableContact.TABLE_NAME);
    public static final Uri CALL_URI = Uri.withAppendedPath(CONTENT_URI, BASE_PATH + TableCall.TABLE_NAME);
    public static final Uri SMS_URI = Uri.withAppendedPath(CONTENT_URI, BASE_PATH + TableSms.TABLE_NAME);
    public static final Uri CONTRIBUTE_URI = Uri.withAppendedPath(CONTENT_URI, BASE_PATH + TableContribute.TABLE_NAME);

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH + TableContact.TABLE_NAME, CONTACT);
    }


    @Override
    public boolean onCreate() {
        mDatabaseManager = DatabaseManager.getInstance(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        int deletedRows;
        int uriType = URI_MATCHER.match(uri);
        switch (uriType) {
            case CONTACT:
                deletedRows = db.delete(TableContact.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return deletedRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        int updatedRows;
        int uriType = URI_MATCHER.match(uri);
        switch (uriType) {
            case CONTACT:
                updatedRows = db.update(TableContact.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return updatedRows;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        long id;
        int uriType = URI_MATCHER.match(uri);
        switch (uriType) {
            case CONTACT:
                id = db.insert(TableContact.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (values.length < 1) {
            return 0;
        }
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        db.beginTransaction();
        try {
            for (ContentValues cv : values) {
                insert(uri, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return super.bulkInsert(uri, values);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}
