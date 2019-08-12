package com.example.asshoanthien.dnhonthin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreatDatabaseFavorites extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "DATA";

    public static final String TABLE = " TABLE_DATA ";
    public static final String ID = " ID ";
    public static final String IMG_STRING = " IMG_STRING ";

    public CreatDatabaseFavorites(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE
                + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IMG_STRING + " TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            String sql = "DROP TABLE IF EXISTS " + TABLE;
            db.execSQL(sql);
        }
    }
}