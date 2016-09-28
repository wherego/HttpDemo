package com.zhao.httpdemo.Entity;

import java.io.Serializable;

/**
 * Created by zhao on 2016/9/27.
 */

public class TestEntity implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
