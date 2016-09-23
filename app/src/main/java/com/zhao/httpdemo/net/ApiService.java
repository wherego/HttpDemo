package com.zhao.httpdemo.net;

import com.zhao.httpdemo.Entity.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhao on 2016/9/22.
 */

public interface ApiService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
/*
    @GET("citylist?cityname={cityname}")
    Observable<BaseResponseEntity<ArrayList<WeatherEntity>>> test(@Path("cityname") String cityname);*/

    @GET("citylist")
    Observable<BaseResponseEntity<ArrayList<WeatherEntity>>> test(@Query("cityname") String cityname);
}

//query
//queryMap

//field
//fieldmap

//part
//partmap