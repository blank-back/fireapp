package com.testapp.template.db;

public class account {
    private String acc ;
    private String password;

    public account(String acc,String password) {
        this.acc = acc;
        this.password = password;
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
}
