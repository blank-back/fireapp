package com.testapp.template.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;

public class ReadDatabase {
    public SQLiteDatabase openDatabase(String filepath) {
        String dbFd=filepath;
        Log.d("info","dbFd="+dbFd);
        String dbfile= dbFd+"/test_db";
        try {
            File fd=new File(dbFd);
            if(!fd.exists()){
                fd.mkdirs();
            }
            File file=new File(dbfile);
            if (!file.exists()) {
                Log.d("info","database not found");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
        return db;
    }
}
