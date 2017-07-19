package de.hauke_stieler.rednimer.Technical.DatabaseUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hauke on 19.07.17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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
}
