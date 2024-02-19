package com.testapp.template.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.testapp.template.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

public class settingshelper {
    sqlitehelper helpor;
    public settingshelper(Context context){
        helpor= GlobalVariable.getInstance().getsqlite();
    }
    public boolean crat(settings his){
        SQLiteDatabase db=helpor.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("test3",his.gettest3());
        values.put("test1",his.gettest1());
        values.put("test2",his.gettest2());
        long i=  db.insert("setting",null,values);
        return i>-1 ? true : false;
    }
    public settings getsetting(String account) {
        settings result = new settings(account,false,false,false);
        SQLiteDatabase db = helpor.getReadableDatabase();
        Cursor query = db.query("setting", null, "account==?", new String[]{account}, null, null, null);
        if (query.moveToFirst()) {
            result.settest1(query.getInt(1)==1?true:false);
            result.settest2(query.getInt(2)==1?true:false);
            result.settest3(query.getInt(3)==1?true:false);
        } else {
            return new settings(account,false,false,false);
        }
        return result;
    }
}
