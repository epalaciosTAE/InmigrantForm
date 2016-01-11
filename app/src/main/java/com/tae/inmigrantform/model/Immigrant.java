package com.tae.inmigrantform.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Eduardo on 10/12/2015.
 */
public class Immigrant implements Parcelable {

    private int id;
    private String familyName;
    private String lastName;
    private String imagePath;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String address;
    private String email;
    private String phone;

    public Immigrant () {
    }

    public Immigrant(String familyName) {
        this.familyName = familyName;
    }

    public Immigrant(String familyName,
                     String lastName,
                     String imagePath,
                     String dateOfBirth,
                     String gender,
                     String nationality,
                     String address,
                     String email,
                     String phone) {
        this.familyName = familyName;
        this.lastName = lastName;
        this.imagePath = imagePath;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Immigrant(int id,
                     String familyName,
                     String lastName,
                     String imagePath,
                     String dateOfBirth,
                     String gender,
                     String nationality,
                     String address,
                     String email,
                     String phone) {
        this.id = id;
        this.familyName = familyName;
        this.lastName = lastName;
        this.imagePath = imagePath;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }



    protected Immigrant(Parcel in) {
        id = in.readInt();
        familyName = in.readString();
        lastName = in.readString();
        imagePath = in.readString();
        dateOfBirth = in.readString();
        gender = in.readString();
        nationality = in.readString();
        address = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    public static final Creator<Immigrant> CREATOR = new Creator<Immigrant>() {
        @Override
        public Immigrant createFromParcel(Parcel in) {
            return new Immigrant(in);
        }

        @Override
        public Immigrant[] newArray(int size) {
            return new Immigrant[size];
        }
    };

    public String getFamilyName() {
        return familyName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(familyName);
        dest.writeString(lastName);
        dest.writeString(imagePath);
        dest.writeString(dateOfBirth);
        dest.writeString(gender);
        dest.writeString(nationality);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(phone);
    }


}
