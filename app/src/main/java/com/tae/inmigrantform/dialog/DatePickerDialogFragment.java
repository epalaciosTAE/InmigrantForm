package com.tae.inmigrantform.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.tae.inmigrantform.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Eduardo on 09/12/2015.
 */
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String FRAGMENT_TAG = "DatePickerDialogFragment";
    private Calendar calendar;
    private OnGetDatePickerListener datePickerListener;

    public static DatePickerDialogFragment newInstance() {
        return new DatePickerDialogFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            datePickerListener = (OnGetDatePickerListener) activity;
        } catch (ClassCastException e) {
            Log.e("DatePicker", "Must implement OnGetDatePickerListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        datePickerListener.getDate(simpleDateFormat.format(calendar.getTime()));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        datePickerListener = null;
    }
}
