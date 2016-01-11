package com.tae.inmigrantform.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.tae.inmigrantform.R;
import com.tae.inmigrantform.constants.Constants;
import com.tae.inmigrantform.model.Immigrant;
import com.tae.inmigrantform.provider.SaveImmigrantTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Eduardo on 14/12/2015.
 */
public class ConfirmationDialogFragment extends DialogFragment{

    public static final String FRAGMENT_TAG = "ConfirmationDialogFragment";

    private Immigrant immigrant;

    @Bind(R.id.tv_confirmation_family_name) protected TextView familyName;
    @Bind(R.id.tv_confirmation_last_name) protected TextView lastName;
    @Bind(R.id.tv_confirmation_image) protected TextView imagePath;
    @Bind(R.id.tv_confirmation_date_of_birth) protected TextView dateOfBirth;
    @Bind(R.id.tv_confirmation_gender) protected TextView gender;
    @Bind(R.id.tv_confirmation_country) protected TextView country;
    @Bind(R.id.tv_confirmation_address) protected TextView address;
    @Bind(R.id.tv_confirmation_mail) protected TextView email;
    @Bind(R.id.tv_confirmation_phone) protected TextView phone;
    @Bind(R.id.btn_confirmation_cancel) protected Button btnCancel;
    @Bind(R.id.btn_confirmation_save) protected Button btnSave;
//    @Bind(R.id.toolbar) protected Toolbar toolbar;


    public static ConfirmationDialogFragment newInstance(Immigrant immigrant) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.ARG_IMMIGRANT, immigrant);
        ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immigrant = (Immigrant) getArguments().get(Constants.ARG_IMMIGRANT);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getTheme());
        View view = getActivity().getLayoutInflater().inflate(R.layout.immigrant_detail, null);
        ButterKnife.bind(this, view);
        setTextInViews();
        setButtonsVisibility();
        builder.setView(view);
        return builder.create();
    }

    private void setButtonsVisibility() {
        btnCancel.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);
    }

    private void setTextInViews() {
        familyName.setText(immigrant.getFamilyName());
        lastName.setText(immigrant.getLastName());
        imagePath.setText(immigrant.getImagePath());
        dateOfBirth.setText(immigrant.getDateOfBirth());
        gender.setText(immigrant.getGender());
        country.setText(immigrant.getNationality());
        address.setText(immigrant.getAddress());
        email.setText(immigrant.getEmail());
        phone.setText(immigrant.getPhone());
    }

    @OnClick({R.id.btn_confirmation_cancel, R.id.btn_confirmation_save})
    protected void onClick(View view) {
        if (view.getId() == R.id.btn_confirmation_save) {
            SaveImmigrantTask saveImmigrantTask = new SaveImmigrantTask(getActivity()); // TODO if the bug here still happens, save data in a different button
            saveImmigrantTask.execute(immigrant);
        }
        dismiss();
    }

}
