package com.tae.inmigrantform.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eduardo on 12/12/2015.
 */
public class ImmigrantDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ImmigrantDB.db";

    public ImmigrantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ImmigrantContract.SQL_CREATE_IMMIGRANT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ImmigrantContract.SQL_DELETE_IMMIGRANT_TABLE);
        onCreate(db);
    }

}
