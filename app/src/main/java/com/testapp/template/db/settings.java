package com.testapp.template.db;

public class settings {
    private String account ;
    private boolean test1;
    private boolean test2;
    private boolean test3;

    public settings(String account,boolean test1,boolean test2,boolean test3) {
        this.account=account;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
    }


    public boolean gettest1() {
        return test1;
    }

    public void settest1(boolean time) {
        this.test1 = test1;
    }

    public boolean gettest2() {
        return test2;
    }

    public void settest2(boolean test2) {
        this.test2 = test2;
    }

    public boolean gettest3() {
        return test2;
    }

    public void settest3(boolean test2) {
        this.test2 = test2;
    }

    public String getaccount() {
        return account;
    }

    public void setaccount(String account) {
        this.account = account;
    }
}
