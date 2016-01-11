package com.tae.inmigrantform.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;

/**
 * Created by Eduardo on 12/12/2015.
 */
public class ImmigrantResolver {

    private final ContentResolver contentResolver;

    public ImmigrantResolver(Context context) {
        this.contentResolver = context.getContentResolver();
    }

    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder)
            throws RemoteException {
        return contentResolver.query(uri,
                projection,
                selection,
                selectionArgs,
                sortOrder);
    }

    public Uri insert(Uri uri,ContentValues cvs) throws RemoteException {
        return contentResolver.insert(uri,cvs);
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) throws RemoteException {
        return contentResolver.delete(uri, selection, selectionArgs);
    }

    public int update(Uri uri,
                      ContentValues cvs,
                      String selection,
                      String[] selectionArgs) throws RemoteException {
        return contentResolver.update(uri, cvs, selection, selectionArgs);
    }

}
