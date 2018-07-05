package com.example.liumengchao20180702.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class Sqlifehappend {
    private Slife slife;
    private SQLiteDatabase database;

    public Sqlifehappend(Context context) {
        slife=new Slife(context);
      database= slife.getReadableDatabase();
    }
public  void insert(String name,boolean selects){
    ContentValues contentValues=new ContentValues();
    contentValues.put("name",name);
    contentValues.put("selects",selects);
    database.insert("user",null,contentValues);
}



}
