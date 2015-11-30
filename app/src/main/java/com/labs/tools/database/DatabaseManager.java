package com.labs.tools.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.labs.tools.database.table.BaseTable;
import com.labs.tools.database.table.TableCall;
import com.labs.tools.database.table.TableContact;
import com.labs.tools.database.table.TableSms;

/**
 * Created by Vikraa on 11/26/2015.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private static final BaseTable[] REPUTASI_TABLES = {
      new TableContact(), new TableCall(), new TableSms()
    };

    public static final String NAME = "reputasi";
    private static final int VERSION = 2;
    private static DatabaseManager sDatabaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (sDatabaseManager == null) {
            sDatabaseManager = new DatabaseManager(context);
        }
        return sDatabaseManager;
    }

    public DatabaseManager(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (BaseTable table : REPUTASI_TABLES) {
            table.onCreateTable(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (BaseTable table : REPUTASI_TABLES) {
            table.onUpgradeTable(db, oldVersion, newVersion);
        }
    }

    public void dropAllTables(SQLiteDatabase db) {
        for (BaseTable table : REPUTASI_TABLES) {
            table.deleteTable(db);
        }
    }

    public void dropTable(SQLiteDatabase db, String tableName) {
        for (BaseTable table : REPUTASI_TABLES) {
            if (table.getTableName().equalsIgnoreCase(tableName)) {
                table.deleteTable(db);
                break;
            }
        }
    }

}
