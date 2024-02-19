package com.testapp.template.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class historyhelper {
    sqlitehelper helpor;
    public historyhelper(Context context){
        helpor= new sqlitehelper(context);
    }
    public boolean crat(history his){
        SQLiteDatabase db=helpor.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("account",his.getaccount());
        values.put("time",his.gettime());
        values.put("answer",his.getanswer());
        long i=  db.insert("history",null,values);
        return i>-1 ? true : false;
    }
    public List<history> searchhis(String account) {
        List<history> result = new ArrayList<>();
        SQLiteDatabase db = helpor.getReadableDatabase();
        Cursor query = db.query("history", null, "account==?", new String[]{account}, null, null, null);
        if (query.moveToFirst()) {
            do {
                int id = query.getInt(0);
                String acc= query.getString(1);
                String time = query.getString(2);
                boolean answer = query.getInt(3) == 1 ? true : false;
                result.add(new history(time, acc, answer));
            } while (query.moveToNext());
        } else {
            return null;
        }
        return result;
    }
}
