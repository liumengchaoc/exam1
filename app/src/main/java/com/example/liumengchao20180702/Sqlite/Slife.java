package com.example.liumengchao20180702.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Slife extends SQLiteOpenHelper{


    public Slife(Context context) {
        super(context, "Data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user(id integer primary key autoincrement," +
                "name text," +
                "selects boolean)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
