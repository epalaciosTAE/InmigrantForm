package com.tae.inmigrantform.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Eduardo on 10/12/2015.
 * Code to save image in SD card
 */
public class PhotoUtils {



    public static Uri createImageFile(Context context, Bitmap imageBitmap) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        final File image = new File(storageDir.getPath() + File.separator  + imageFileName+ ".jpg");
        notifyMediaScanners(context, image);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        try {

            FileOutputStream fo = new FileOutputStream(image);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            Log.e("MAIN", "Error saving bitmap " + e);
        }

//        photoFromCameraPath = "file:" + image.getAbsolutePath();
        return Uri.fromFile(image);
    }

    public static void notifyMediaScanners(Context context,File file) {
        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(
                context,
                new String[]{file.toString()},
                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {

                    }
                });
    }

}
