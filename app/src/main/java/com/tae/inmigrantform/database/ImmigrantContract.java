package com.tae.inmigrantform.database;

import android.provider.BaseColumns;

/**
 * Created by Eduardo on 11/12/2015.
 */
public final class ImmigrantContract {

    public static abstract class ImmigrantEntry implements BaseColumns {
        public static final String TABLE_IMMIGRANT = "immigrant_data_base";
        public static final String COLUMN_NAME_FAMILY_NAME = "family_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE = "phone";
    }

    public static final String TEXT = " TEXT";
    public static final String CREATE = "CREATE TABLE ";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    public static final String PK = " INTEGER PRIMARY KEY" ;
    public static final String COMA = ", ";

    public static final String SQL_CREATE_IMMIGRANT_TABLE =
            CREATE + ImmigrantEntry.TABLE_IMMIGRANT + " (" +
                    ImmigrantEntry._ID + PK + COMA +
                    ImmigrantEntry.COLUMN_NAME_FAMILY_NAME + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_LAST_NAME + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_IMAGE + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_DATE_OF_BIRTH + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_GENDER + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_COUNTRY + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_ADDRESS + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_EMAIL + TEXT + COMA +
                    ImmigrantEntry.COLUMN_NAME_PHONE + TEXT + " )";

    public static final String SQL_DELETE_IMMIGRANT_TABLE =
            DROP_TABLE + ImmigrantEntry.TABLE_IMMIGRANT;
}
