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
        values.put("manager",user.getManager()?1:0);
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
        tmp.setFilepath("");
        tmp.setAccount("");
        tmp.setLoginState(false);
        tmp.setManager(false);
        SQLiteDatabase db = helpor.getWritableDatabase();
        Cursor query = db.query("global", new String[]{"login_state","account","filepath","manager"}, "id=?", new String[]{"1"}, null, null, null);
        if (query.moveToFirst()) {
            tmp.setLoginState(query.getInt(0)==1?true:false);
            tmp.setAccount(query.getString(1));
            tmp.setManager(query.getInt(3)==1?true:false);
            return tmp;
        } else {
            Log.d("info","没找到！");
            return tmp;
        }
    }
}
