package com.zhao.httpdemo.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhao on 2016/9/23.
 */

public class BaseResponseEntity<T> implements Serializable{
    //Http response status
    public int status;

    public int code;

    public String message;

    public T data;

    public boolean success(){
        return code == 20000;
    }
}
