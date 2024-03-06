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
        if(user.getpassword().equals("")) {
            Cursor query = db.query("user", new String[]{"account", "password"}, "account=?", new String[]{user.getaccount()}, null, null, null);
            if (query.moveToFirst()) {
                user.setpassword(query.getString(1));
                values.put("account",user.getaccount());
                values.put("password",user.getpassword());
                values.put("manager",user.getmanager()?1:0);
                Log.d("manager or not",user.getmanager()?"true":"false");
                long i=  db.update("user",values,"account=?",new String[]{user.getaccount()});
                return i>-1 ? true : false;
            } else {
                Log.d("info","没填密码也找不到人");
                return false;
            }
        }
        values.put("account",user.getaccount());
        values.put("password",user.getpassword());
        values.put("manager",user.getmanager()?1:0);
        long i=  db.insert("user",null,values);
        return i>-1 ? true : false;
    }
    public boolean verify(account user) {
        SQLiteDatabase db = helpor.getReadableDatabase();
        Cursor query = db.query("user", new String[]{"account","password"}, "account=?", new String[]{user.getaccount()}, null, null, null);
        if (query.moveToFirst()) {
                if(user.getpassword().equals(query.getString(1)))
                    return true;
                return false;
        } else {
            return false;
        }
    }
    public boolean getmanager(String acc) {
        SQLiteDatabase db = helpor.getReadableDatabase();
        Cursor query = db.query("user", new String[]{"manager"}, "account=?", new String[]{acc}, null, null, null);
        if (query.moveToFirst()) {
            return query.getInt(0)==1;
        } else {
            Log.d("info","存在绕过登录的可能性");
            return false;
        }
    }
    public account getaccount(String acc) {
        SQLiteDatabase db = helpor.getReadableDatabase();
        Cursor query = db.query("user", new String[]{"account","password","manager"}, "account=?", new String[]{acc}, null, null, null);
        if (query.moveToFirst()) {
            return new account(query.getString(0),query.getString(1),query.getInt(2)==1);
        } else {
            Log.d("info","用户不存在");
            return null;
        }
    }
}
