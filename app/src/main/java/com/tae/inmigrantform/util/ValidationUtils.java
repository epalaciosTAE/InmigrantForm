package com.tae.inmigrantform.util;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;

import com.tae.inmigrantform.R;

/**
 * Created by Eduardo on 10/12/2015.
 */
public class ValidationUtils {

    public static boolean validateName(String name) {
        if (name.trim().isEmpty()) {
            return false;
        }
        return true;
    }

//    private boolean validateEmail(String email) {
//        if (email.isEmpty() || !isValidEmail(email)) {
//            return false;
//        }
//        return true;
//    }

//    private boolean validatePassword() {
//        if (inputPassword.getText().toString().trim().isEmpty()) {
//            inputLayoutPassword.setError(getString(R.string.err_msg_password));
//            requestFocus(inputPassword);
//            return false;
//        } else {
//            inputLayoutPassword.setErrorEnabled(false);
//        }
//
//        return true;
//    }
//
//    private boolean validatePhonenumber() {
//        String phone_number = inputPhoneNumber.getText().toString();
//        Integer phone_number_integer = Integer.getInteger(phone_number);
//        if(PhoneNumberUtils.isEmergencyNumber(phone_number)){
//            return false;
//        }
//        else if(PhoneNumberUtils.isGlobalPhoneNumber(phone_number)){
//            return true;
//        }
//        return false;
//    }
//    private static boolean isValidEmail(String email) {
//        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
}
