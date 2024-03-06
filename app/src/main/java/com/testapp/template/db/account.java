package com.testapp.template.db;

public class account {
    private String acc ;
    private String password;
    private boolean manager;

    public account(String acc,String password,boolean manager) {
        this.acc = acc;
        this.password = password;
        this.manager=manager;
    }


    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getaccount() {
        return acc;
    }

    public void setaccount(String acc) {
        this.acc = acc;
    }

    public boolean getmanager() {
        return manager;
    }

    public void setmanager(boolean manager) {
        this.manager = manager;
    }
}
