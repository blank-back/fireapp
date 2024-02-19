package com.testapp.template.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class accounthelper {
    sqlitehelper helpor;
    public accounthelper(Context context){
        helpor= new sqlitehelper(context);
    }
    public boolean crat(account user){
        SQLiteDatabase db=helpor.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("account",user.getaccount());
        values.put("password",user.getpassword());
        long i=  db.insert("user",null,values);
        return i>-1 ? true : false;
    }
    public boolean verify(account user) {
        SQLiteDatabase db = helpor.getReadableDatabase();
        Cursor query = db.query("user", new String[]{"account","password"}, "account=?", new String[]{user.getaccount()}, null, null, null);
        if (query.moveToFirst()) {
            Log.d("account",user.getaccount());
            Log.d("pwd",user.getpassword());
            Log.d("account",query.getString(0));
            Log.d("pwd",query.getString(1));
                if(user.getpassword().equals(query.getString(1)))
                    return true;
                return false;
        } else {
            return false;
        }
    }
}
