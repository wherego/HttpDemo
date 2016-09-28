package com.zhao.httpdemo.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhao on 2016/9/28.
 */

public class LoginRequest implements Serializable {
    public void setType(int type) {
        this.type = type;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int type;
    @SerializedName("phone_number")
    private String phoneNumber;
    private String password;

    public static class Builder {

    }
}
