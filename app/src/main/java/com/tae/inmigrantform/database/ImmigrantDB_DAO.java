package com.tae.inmigrantform.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tae.inmigrantform.model.Immigrant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 13/12/2015.
 */
public class ImmigrantDB_DAO {

    private static ImmigrantDB_DAO instance;
    private Context context;
    private ImmigrantDBHelper helper;

    private ImmigrantDB_DAO (Context context) {
        this.context = context;
    }

    public static ImmigrantDB_DAO getDAO (Context context) {
        if (instance == null) {
            instance = new ImmigrantDB_DAO(context);
        }
        return instance;
    }

    private void openDataBase() {
        helper = new ImmigrantDBHelper(context);
    }


    public boolean insert(Immigrant immigrant) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = getContentValues(immigrant);
        long rowId = 0;
        try {
            rowId = db.insert(
                    ImmigrantContract.ImmigrantEntry.TABLE_IMMIGRANT,
                    ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME,
                    values);
        } catch (SQLiteException e) {
            Log.e("ImmigrantDAO", "Error inserting row in Draft DB " + e + "\n rowId value = " + rowId);
        } finally {
            db.close();
        }
        return rowId > -1;
    }

    @NonNull
    private ContentValues getContentValues(Immigrant immigrant) {
        ContentValues values = new ContentValues();
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME,immigrant.getFamilyName());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_LAST_NAME, immigrant.getLastName());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_IMAGE, immigrant.getImagePath());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_DATE_OF_BIRTH, immigrant.getDateOfBirth());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_GENDER, immigrant.getGender());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_COUNTRY, immigrant.getNationality());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_ADDRESS, immigrant.getAddress());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_EMAIL, immigrant.getEmail());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_PHONE, immigrant.getPhone());
        return values;
    }

    public List<Immigrant> getImmigrants() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {
                ImmigrantContract.ImmigrantEntry._ID,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME
        };
        String sortOrder =
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME + " DESC";
        List<Immigrant> draftNames = null;
        try {
            Cursor cursor = db.query(
                    ImmigrantContract.ImmigrantEntry.TABLE_IMMIGRANT,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder);
            cursor.moveToFirst();
            draftNames = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                String draftName = cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME));
                draftNames.add(new Immigrant(draftName));
            }
        } catch (SQLiteException e) {
            Log.e("ImmigrantDAO", "Error reading rows in Draft DB " + e);
        } finally {
            db.close();
        }

        return draftNames;
    }
}
