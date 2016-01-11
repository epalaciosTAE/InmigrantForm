package com.tae.inmigrantform.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tae.inmigrantform.database.ImmigrantContract;
import com.tae.inmigrantform.database.ImmigrantDBHelper;

/**
 * Created by Eduardo on 12/12/2015.
 */
public class ImmigrantProvider extends ContentProvider {

    private static final String AUTHORITY = "com.tae.inmigrantform.provider.ImmigrantContentProvider";
    private static final String IMMIGRANT_BASE_PATH = "immigrant";
    private static final String URL_IMMIGRANT = "content://" + AUTHORITY + "/" + IMMIGRANT_BASE_PATH;
    public static final Uri CONTENT_URI_IMMIGRANTS = Uri.parse(URL_IMMIGRANT);
    public static final int IMMIGRANTS = 100;
    public static final int IMMIGRANT_ID = 110;

    private ImmigrantDBHelper immigrantDBHelper;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, IMMIGRANT_BASE_PATH, IMMIGRANTS);
        sURIMatcher.addURI(AUTHORITY, IMMIGRANT_BASE_PATH + "/#", IMMIGRANT_ID);
    }

    public static Uri buildUri(Long id) {
        return android.content.ContentUris.withAppendedId(CONTENT_URI_IMMIGRANTS, id);
    }


    @Override
    public boolean onCreate() {
        immigrantDBHelper = new ImmigrantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(ImmigrantContract.ImmigrantEntry.TABLE_IMMIGRANT);
//        if (IMMIGRANTS == sURIMatcher.match(uri)) {
//            StringBuilder where = new StringBuilder(DatabaseContract.ImmigrantEntry._ID);
//            where.append("=");
//            where.append(uri.getLastPathSegment());
//            sqLiteQueryBuilder.appendWhere(where);
//        } else {
//            throw new IllegalArgumentException("Unkown Uri");
//        }
        return sqLiteQueryBuilder.query(
                immigrantDBHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = immigrantDBHelper.getWritableDatabase();
        long insertionId = 0;
        try {
            if (IMMIGRANTS == sURIMatcher.match(uri)) {
                insertionId = database.insert(ImmigrantContract.ImmigrantEntry.TABLE_IMMIGRANT, null, values);
            }
        } catch (Exception e) {
            Log.e("ImmigrantProvider", "error inserting immigrant " + e.getMessage());
        } finally {
            database.close();
        }
        if (insertionId > 0) {
            notifyChange(uri);
            return buildUri(insertionId);
        } else {
            throw new android.database.SQLException("Failed to insert row into " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = immigrantDBHelper.getWritableDatabase();
        int deletionId = 0;
        if (IMMIGRANTS == sURIMatcher.match(uri)) {
            deletionId = database.delete(ImmigrantContract.ImmigrantEntry.TABLE_IMMIGRANT, selection, selectionArgs);
            notifyChange(uri);
        }
        return deletionId;
    }

    private void notifyChange(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = immigrantDBHelper.getWritableDatabase();
        int count = 0;
        if (IMMIGRANTS == sURIMatcher.match(uri)) {
            count = database.update(ImmigrantContract.ImmigrantEntry.TABLE_IMMIGRANT, values, selection, selectionArgs);
            notifyChange(uri);
        }
        return count;
    }


}
