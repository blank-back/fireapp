package com.testapp.template.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class sqlitehelper extends SQLiteOpenHelper {

    public static String CREATE_USER = "create table user (" +
            "account text primary key," +
            "password text)";
    public static String CREATE_SETTING = "create table setting (" +
            "account text primary key," +
            "test1 bool," +
            "test2 bool," +
            "test3 bool)";
    public static String CREATE_HISTORY = "create table history (" +
            "id integer primary key autoincrement," +
            "time datetime," +
            "account text," +
            "answer text)";
    public static String CREATE_GLOBAL = "create table global (" +
            "id integer primary key," +
            "login_state bool," +
            "account text," +
            "filepath text)";

    Context mContext;

    public sqlitehelper(@Nullable Context context) {
        super(context, "test", null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_SETTING);
        sqLiteDatabase.execSQL(CREATE_HISTORY);
        sqLiteDatabase.execSQL(CREATE_GLOBAL);
    }

    @Override
    public  void  onUpgrade(SQLiteDatabase db,  int  oldVersion,  int  newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists setting");
        db.execSQL("drop table if exists history");
        db.execSQL("drop table if exists global");
        onCreate(db);
    }
}