package com.avenjr.me.me.modle;

public class User {

    private String id = "", password = "";
    private long mobileNumber;
    private static User user;

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }

        return user;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
