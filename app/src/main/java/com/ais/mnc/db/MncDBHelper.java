package com.ais.mnc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.ais.mnc.constant.TableConstant.*;

/**
 * Copyright (C) 2018 CYu AIS. All rights reserved.
 * Description:
 * Created on 18/10/2018
 *
 * @author Shaw
 * @version 1.0
 */
public class MncDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "MncDBHelper >>> ";

    public static final String DATABSE_NAME = "dbMNC";
    public static final int DATABASE_VERSION = 1;

    public MncDBHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_VEHICLE_TABLE);
        db.execSQL(CREATE_CAMP_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_PHOTO_TABLE);

        Log.d(TAG, "init............: ");
        //add default admin user account
//        ContentValues userV1 = new ContentValues();
//        userV1.put(TableConstant.USER_COL1_UID,    1);
//        userV1.put(TableConstant.USER_COL2_UNAME,  "admin");
//        userV1.put(TableConstant.USER_COL3_EMAIL,  "shaw.yunz@gmail.com");
//        userV1.put(TableConstant.USER_COL4_PWD,    "adminshaw");
//        userV1.put(TableConstant.USER_COL5_TYPE,   "admin");
//        db.insert(TableConstant.USER_TABLE_NAME, null, userV1);

        String query_u_init = "INSERT INTO "    + USER_TABLE_NAME
                + " SELECT 1 AS "               + USER_COL1_UID
                + ", 'admin' AS "               + USER_COL2_UNAME
                + ", 'shaw.yunz@gmail.com' AS " + USER_COL3_EMAIL
                + ", 'adminshaw' AS "           + USER_COL4_PWD
                + ", 'admin' AS "               + USER_COL5_TYPE
                + " UNION SELECT 2, 'aaa', 'aaa', 'aaa', 'user'"
                ;
        db.execSQL(query_u_init);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VEHICLE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CAMP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PHOTO_TABLE_NAME);
        this.onCreate(db);
    }
}
