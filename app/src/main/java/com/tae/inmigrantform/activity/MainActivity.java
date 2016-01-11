package com.tae.inmigrantform.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.tae.inmigrantform.R;
import com.tae.inmigrantform.dialog.ConfirmationDialogFragment;
import com.tae.inmigrantform.dialog.DatePickerDialogFragment;
import com.tae.inmigrantform.dialog.OnGetDatePickerListener;
import com.tae.inmigrantform.listener.IsTaskFinished;
import com.tae.inmigrantform.model.Immigrant;
import com.tae.inmigrantform.util.PhotoUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener, OnGetDatePickerListener, IsTaskFinished {

    private static final int REQUEST_CAMERA_IMAGE  = 666;
    private static final int [] SPARSE_EDIT_TEXT_VIEW = {0,1,2,3,4,5};
//    public static final String EXTRA_IMMIGRANT = "extra_immigrant";
    private static final int PERMISSIONS_REQUEST_CAMERA = 111;
    private static final int PERMISSIONS_REQUEST_WRITE_STORAGE = 112;
    private String imagePath;
    private String gender;
    private String nationality;

    @Bind(R.id.image) protected ImageView image;
    @Bind(R.id.family_name) protected EditText etFamilyName;
    @Bind(R.id.et_family_last_name) protected EditText etLastName;
    @Bind(R.id.et_address) protected EditText etAddress;
    @Bind(R.id.et_email) protected EditText etEmail;
    @Bind(R.id.et_phone) protected EditText etPhone;
    @Bind(R.id.btn_to_date_picker) protected Button btnDatePicker;
//    @Bind(R.id.btn_cancel) protected Button btnCancel;
    @Bind(R.id.rg_status) protected RadioGroup radioGroupStatus;
    @Bind(R.id.spinner_country) protected Spinner spinnerCountry;
    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.fab) protected FloatingActionButton fabSave;


    // Here, thisActivity is the current activity
    private boolean hasCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},PERMISSIONS_REQUEST_CAMERA);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private boolean hasStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_WRITE_STORAGE);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            }
        }

//        if (requestCode == PERMISSIONS_REQUEST_WRITE_STORAGE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//            }
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        setListeners();
        hasStoragePermission();
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.toolbar_title_form);
        setSupportActionBar(toolbar);
    }

    private void setListeners() {
        radioGroupStatus.setOnCheckedChangeListener(this);
        spinnerCountry.setOnItemSelectedListener(this);
    }

    @OnClick({R.id.btn_to_date_picker, R.id.image, R.id.fab})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_date_picker :
                Log.i("MAIN", "click");
                showDatePicker();
                break;
            case R.id.image :
                if (hasCameraPermission()) {
                    launchCamera();
                }
                break;
            case R.id.fab :
//                showConfirmationActivity();
                showConfirmationDialog(v);
                break;
        }
    }

    private void showConfirmationDialog(View view) {
        if (isFormComplete()) {
//            showToast("Confirm immigrant's data");
            showSnackBar(view, "Confirm immigrant's data");
            Immigrant immigrant = getImmigrant();
            ConfirmationDialogFragment.newInstance(immigrant)
                    .show(getFragmentManager(), ConfirmationDialogFragment.FRAGMENT_TAG);
        } else {
//            showToast("Please fill all the edit text fields");
            showSnackBar(view, "Please fill all the edit text fields");

        }
    }

    private void showSnackBar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_SHORT).show();
    }

    @NonNull
    private Immigrant getImmigrant() {
        return new Immigrant(
                etFamilyName.getText().toString(),
                etLastName.getText().toString(),
                imagePath,
                btnDatePicker.getText().toString(),
                gender,
                nationality,
                etAddress.getText().toString(),
                etEmail.getText().toString(),
                etPhone.getText().toString()
        );
    }

    private void launchCamera() {
        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CAMERA_IMAGE);
    }

    private void showDatePicker() {
        DatePickerDialogFragment datePicker = DatePickerDialogFragment.newInstance();
        datePicker.show(getFragmentManager(), DatePickerDialogFragment.FRAGMENT_TAG);
    }

//    private void showConfirmationActivity() {
//        if (isFormComplete()) {
//            showToast("Confirm immigrant's data");
//            startActivity(new Intent(MainActivity.this, DetailActivity.class)
//                    .putExtra(Constants.EXTRA_IMMIGRANT, getImmigrant()));
//        } else {
//            showToast("Please fill all the edit text fields");
//        }
//    }

    private boolean isFormComplete() {
        return !etFamilyName.getText().toString().isEmpty()
                && !etLastName.getText().toString().isEmpty()
                && (!etEmail.getText().toString().isEmpty())
                && !etAddress.getText().toString().isEmpty()
                && !etPhone.getText().toString().isEmpty()
                && !etPhone.getText().toString().isEmpty()
                && radioGroupStatus.getCheckedRadioButtonId() > 0
                && !btnDatePicker.getText().toString().equals(getResources().getString(R.string.dob))
                && nationality != null;
    }


    private SparseArray<EditText> getFields() {
        SparseArray<EditText> fields = new SparseArray<>(5);
        fields.put(SPARSE_EDIT_TEXT_VIEW [0], etAddress);
        fields.put(SPARSE_EDIT_TEXT_VIEW[1], etEmail);
        fields.put(SPARSE_EDIT_TEXT_VIEW[2], etFamilyName);
        fields.put(SPARSE_EDIT_TEXT_VIEW[3], etLastName);
        fields.put(SPARSE_EDIT_TEXT_VIEW[4], etPhone);
        return fields;
    }

    private void cleanForm(SparseArray<EditText> fields) {
        for (int i = 0 ; i < fields.size(); i++) {
            int editTextKey = fields.keyAt(i);
            fields.get(editTextKey).setText("");
        }
        btnDatePicker.setText(getResources().getString(R.string.dob));
        radioGroupStatus.clearCheck();
        spinnerCountry.setSelection(0);
        image.setImageResource(android.R.drawable.star_big_off);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CAMERA_IMAGE) {

        // TODO continue
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            Uri uriImage = null;
            try {
                uriImage = PhotoUtils.createImageFile(this, imageBitmap);
            } catch (IOException e) {
                Log.e("MainActivity", "Error saving file " + e.getMessage());
            }
            image.setImageBitmap(imageBitmap);
            imagePath = String.valueOf(uriImage);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_male) {
            gender = getResources().getString(R.string.male);
        } else {
            gender = getResources().getString(R.string.female);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("MAIN", "position spinner " + position);
        nationality = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showToast (String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void getDate(String date) {
        btnDatePicker.setText(date);
    }


    @Override
    protected void onResume() {
        super.onResume();
        validateEmail();
        validatePhoneNumber();
    }

    private void validateEmail() {
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.getId() == R.id.et_email && !hasFocus) {
                    if (!etEmail.getText().toString().isEmpty() && !isEmailValid(etEmail.getText().toString())) {
                        showToast(getString(R.string.error_email_form));
                    }
                }
            }
        });
    }

    private void validatePhoneNumber() {
        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.getId() == R.id.et_phone && !hasFocus) {
                    if (PhoneNumberUtils.isEmergencyNumber(etPhone.getText().toString())) {
                       showToast("Emergency Numbers are not available");
                        etPhone.setText("");
                    } else if (PhoneNumberUtils.isGlobalPhoneNumber(etPhone.getText().toString())) {
                        showToast("Global Number aren't available");
                        etPhone.setText("");
                    }
                }
            }
        });
    }

    @Override
    public void isFinished(boolean finished) {
        if (finished) {
            startActivity(new Intent(MainActivity.this, ImmigrantListActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_clear) {
            cleanForm(getFields());
            Snackbar.make(ButterKnife.findById(this, R.id.menu_item_clear), "Form cleaned", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
