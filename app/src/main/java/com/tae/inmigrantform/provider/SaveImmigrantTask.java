package com.tae.inmigrantform.provider;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.tae.inmigrantform.listener.IsTaskFinished;
import com.tae.inmigrantform.database.ImmigrantContract;
import com.tae.inmigrantform.model.Immigrant;

/**
 * Created by Eduardo on 12/12/2015.
 * Task to save data in the background
 */
public class SaveImmigrantTask extends AsyncTask<Immigrant, Void, Boolean> {

    private Context context;
    private IsTaskFinished taskFinished;

    public SaveImmigrantTask(Context context) {
        this.context = context;
        taskFinished = (IsTaskFinished) context;
    }

    @Override
    protected Boolean doInBackground(Immigrant... params) {
        ImmigrantResolver resolver = new ImmigrantResolver(context);
        ContentValues values = new ContentValues();
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME,params[0].getFamilyName());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_LAST_NAME,params[0].getLastName());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_IMAGE,params[0].getImagePath());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_DATE_OF_BIRTH,params[0].getDateOfBirth());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_GENDER,params[0].getGender());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_COUNTRY,params[0].getNationality());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_ADDRESS,params[0].getAddress());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_EMAIL,params[0].getEmail());
        values.put(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_PHONE,params[0].getPhone());
        Uri result = null;
        try {
            result = resolver.insert(ImmigrantProvider.CONTENT_URI_IMMIGRANTS, values);
        } catch (RemoteException e) {
            Log.e("SaveImmigrantTask", "Error saving immigrant " + e.getMessage());
        }
        return result != null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean) {
            Toast.makeText(context, "Immigrant data saved", Toast.LENGTH_SHORT).show();
        }
        taskFinished.isFinished(true);
    }


}
