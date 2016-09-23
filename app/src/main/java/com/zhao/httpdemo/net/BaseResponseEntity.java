package com.zhao.httpdemo.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhao on 2016/9/23.
 */

public class BaseResponseEntity<T> implements Serializable{
    @SerializedName("errNum")
    public String error;

    @SerializedName("errMsg")
    public String msg;

    @SerializedName("retData")
    public T results;


    public boolean success(){
        return error.equals("0");
    }
}
