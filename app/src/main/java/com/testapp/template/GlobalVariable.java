package com.testapp.template;

import android.util.Log;
import com.testapp.template.db.globalhelper;
import com.testapp.template.db.sqlitehelper;

// 使用单例模式定义全局变量
public class GlobalVariable {
    private static GlobalVariable instance;
    private boolean login_state;
    private String filepath;
    private String account;
    private sqlitehelper helpor;
    private globalhelper gh;
    public GlobalVariable() {

    }

    public static synchronized GlobalVariable getInstance() {
        if (instance == null) {
            instance = new GlobalVariable();
            instance.login_state=false;
            instance.filepath="";
            instance.account="";
            instance.helpor=null;
            instance.gh=null;
        }
        return instance;
    }

    public boolean getLoginState() {
        return login_state;
    }

    public void setLoginState(boolean login_state) {
        this.login_state = login_state;
        if(gh!=null)
        gh.crat(this);
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
        if(gh!=null)
            gh.crat(this);
    }

    public sqlitehelper getsqlite() {
        return helpor;
    }

    public void setsqlite(sqlitehelper helpor) {
        this.helpor = helpor;
        this.gh=new globalhelper(helpor);
        Log.d("info",gh==null?"null":"non-null");
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        if(gh!=null)
            gh.crat(this);
    }
    public void update()
    {
        GlobalVariable tmp=gh.read();
        if(tmp==null)
            return;
        setLoginState(tmp.getLoginState());
        setAccount(tmp.getAccount());
        setFilepath(tmp.getFilepath());
    }
}