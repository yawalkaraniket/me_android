package com.avenjr.me.me.modle;

import android.text.TextUtils;
import android.util.Patterns;

public class User {

    public String id, password;

    public User() {}

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

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public boolean isValidData() {
        return !TextUtils.isEmpty(getId()) && Patterns.EMAIL_ADDRESS.matcher(getId()).matches()
                && getPassword().length() > 6;
    }
}
