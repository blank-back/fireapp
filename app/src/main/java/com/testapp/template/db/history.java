package com.testapp.template.db;

public class history {
    private String time;
    private String account ;
    private boolean answer;

    public history(String account,String time,boolean answer) {
        this.account = account;
        this.time = time;
        this.answer = answer;
    }


    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public boolean getanswer() {
        return answer;
    }

    public void setanswer(boolean answer) {
        this.answer = answer;
    }

    public String getaccount() {
        return account;
    }

    public void setaccount(String account) {
        this.account = account;
    }
}
