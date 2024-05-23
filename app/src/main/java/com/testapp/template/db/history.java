package com.testapp.template.db;

public class history {
    private String time;
    private String account ;
    private double reliability;
    private boolean answer;

    public history(String account,String time,boolean answer,double reliability) {
        this.account = account;
        this.time = time;
        this.answer = answer;
        this.reliability=reliability;
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

    public double getreliability() {
        return reliability;
    }

    public void setreliability(double reliability) {
        this.reliability = reliability;
    }
}
