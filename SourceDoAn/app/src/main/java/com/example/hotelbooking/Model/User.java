package com.example.hotelbooking.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String pass;
    private String phonenumber;

    public User(String pass, String phonenumber) {
        this.pass = pass;
        this.phonenumber = phonenumber;
    }

    public User() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
