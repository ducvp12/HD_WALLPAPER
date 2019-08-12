package com.example.asshoanthien.dnhonthin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.asshoanthien.dnhonthin.model.ModelFavorites;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    private SQLiteDatabase db;
    private CreatDatabaseFavorites creatDatabaseFavorites;

    public DAO(Context context) {
        creatDatabaseFavorites = new CreatDatabaseFavorites(context);
    }

    public void open() {
        db = creatDatabaseFavorites.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void Them(ModelFavorites modelFavorites,Context context) {
        ContentValues values = new ContentValues();
        values.put(CreatDatabaseFavorites.IMG_STRING, modelFavorites.getImgContent());
        db.insert(CreatDatabaseFavorites.TABLE, null, values);
        Toast.makeText(context, "Them thanh cong", Toast.LENGTH_SHORT).show();
    }

    public List<ModelFavorites> getData() {

        List<ModelFavorites> data = new ArrayList<>();

        String statement = "SELECT * FROM " + CreatDatabaseFavorites.TABLE;
        Cursor cursor = db.rawQuery(statement, null);

        cursor.moveToFirst();
        try {
            while (!cursor.isAfterLast()) {

                String img = cursor.getString(1);

                ModelFavorites md = new ModelFavorites(img);

                data.add(md);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            Log.e("CCC", "" + e.getMessage());
        }

        return data;
    }
}
