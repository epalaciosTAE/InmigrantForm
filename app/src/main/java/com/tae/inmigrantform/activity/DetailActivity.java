package com.tae.inmigrantform.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tae.inmigrantform.R;
import com.tae.inmigrantform.constants.Constants;
import com.tae.inmigrantform.model.Immigrant;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Eduardo on 10/12/2015.
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.tv_confirmation_family_name) protected TextView familyName;
    @Bind(R.id.tv_confirmation_last_name) protected TextView lastName;
    @Bind(R.id.tv_confirmation_image) protected TextView imagePath;
    @Bind(R.id.tv_confirmation_date_of_birth) protected TextView dateOfBirth;
    @Bind(R.id.tv_confirmation_gender) protected TextView gender;
    @Bind(R.id.tv_confirmation_country) protected TextView country;
    @Bind(R.id.tv_confirmation_address) protected TextView address;
    @Bind(R.id.tv_confirmation_mail) protected TextView email;
    @Bind(R.id.tv_confirmation_phone) protected TextView phone;
    @Bind(R.id.toolbar) protected Toolbar toolbar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setToolbar();
        hideBackgroundInViews();
        setSupportActionBar(toolbar);
        if (getIntent() != null) {
            Immigrant immigrant = getIntent().getParcelableExtra(Constants.EXTRA_IMMIGRANT);
            Log.i(this.getLocalClassName(), "immigrant " + immigrant.toString());
            setTextInViews(immigrant);
        }
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.toolbar_title_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_bold_pink_24x24);
        toolbar.setNavigationOnClickListener(this);
    }

    private void hideBackgroundInViews() {
        RelativeLayout relativeLayout = ButterKnife.findById(this, R.id.layout_confirmation);
        relativeLayout.setBackground(null);
        TextView title = ButterKnife.findById(this, R.id.tv_confirmation_title);
        title.setBackground(null);
    }

    private void setTextInViews(Immigrant immigrant) {
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


    @Override
    public void onClick(View v) {
        finish();
    }
}
