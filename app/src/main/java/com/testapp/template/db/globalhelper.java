package com.testapp.template.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.testapp.template.GlobalVariable;

public class globalhelper {
    sqlitehelper helpor;
    public globalhelper(sqlitehelper helpor){
        this.helpor= helpor;
    }
    public boolean crat(GlobalVariable user){
        SQLiteDatabase db=helpor.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",1);
        values.put("login_state",user.getLoginState());
        values.put("account",user.getAccount());
        values.put("filepath",user.getFilepath());
        long i;
        Cursor query = db.query("global", null, "id=?",new String[]{"1"}, null, null, null);
        if(query.moveToFirst())
            i=  db.update("global",values,"id=?",new String[]{"1"});
        else
            i=db.insert("global",null,values);
        return i>-1 ? true : false;
    }
    public GlobalVariable read() {
        GlobalVariable tmp = new GlobalVariable();
        SQLiteDatabase db = helpor.getReadableDatabase();
        Cursor query = db.query("global", new String[]{"login_state","account","filepath"}, "id=?", new String[]{"1"}, null, null, null);
        if (query.moveToFirst()) {
            tmp.setLoginState(query.getInt(1)==1?true:false);
            tmp.setAccount(query.getString(2));
            tmp.setFilepath(query.getString(3));
            return tmp;
        } else {
            Log.d("info","没找到！");
            return null;
        }
    }
}
