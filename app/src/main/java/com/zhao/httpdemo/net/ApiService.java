package com.zhao.httpdemo.net;

import com.zhao.httpdemo.Entity.LoginRequest;
import com.zhao.httpdemo.Entity.LoginResponse;
import com.zhao.httpdemo.Entity.RepoEntity;
import com.zhao.httpdemo.Entity.TestEntity;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhao on 2016/9/22.
 */

public interface ApiService {

    @GET("users/{user}/repos")
    Call<List<RepoEntity>> listRepos(@Path("user") String user);
//
//    @GET("citylist?cityname={cityname}")
//    Observable<BaseResponseEntity<ArrayList<WeatherEntity>>> test(@Path("cityname") String cityname);
//
//    @GET("citylist")
//    Observable<BaseResponseEntity<ArrayList<WeatherEntity>>> test(@Query("cityname") String cityname);

    @GET("message/v1/test")
    Observable<BaseResponseEntity<TestEntity>> test();

    @POST("platform/v1/user/login")
    Observable<BaseResponseEntity<LoginResponse>> login(@Body LoginRequest loginRequest);

    //上传单文件
    @Multipart
    @POST("gaga")
    Observable<BaseResponseEntity<ResponseBody>> uploadFile(@Part MultipartBody.Part file);

    //上传多文件
    @Multipart
    @POST("hahah")
    Observable<BaseResponseEntity<ResponseBody>> uploadFiles(@Part() List<MultipartBody.Part> files);

}
