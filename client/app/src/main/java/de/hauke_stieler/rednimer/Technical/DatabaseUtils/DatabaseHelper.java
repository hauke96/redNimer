package de.hauke_stieler.rednimer.Technical.DatabaseUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import juard.contract.Contract;

/**
 * Created by hauke on 19.07.17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DatabaseScheme.DATABASE_NAME, null, DatabaseScheme.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseScheme.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The local database is just a cache from the remote one. Therefore we won't do any versioning here or keep data.
        db.execSQL(DatabaseScheme.DROP_DATABASE_SCRIPT);
        db.execSQL(DatabaseScheme.CREATE_SCRIPT);
    }

    public void insert(ContentValues values, String tableName){
        Contract.NotNull(values);
        Contract.NotNull(tableName);
        Contract.Satisfy(!tableName.trim().isEmpty());

        long result = getWritableDatabase().insert(tableName, null, values);

        if(result == -1){
            String errorMessage = "Error while writing to database table " + tableName;

            Log.e("insert", errorMessage);
            throw new SQLiteException(errorMessage);
        }
    }

    public Cursor runQuery(String query) {
        Contract.NotNull(query);
        Contract.Satisfy(!query.trim().isEmpty());

        return getReadableDatabase().rawQuery(query, null);
    }
}
