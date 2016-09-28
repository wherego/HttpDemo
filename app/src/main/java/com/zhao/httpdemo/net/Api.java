package com.zhao.httpdemo.net;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhao on 2016/9/23.
 */

public class Api {
    private static ApiService SERVICE;
//    private static final String BASE_URL = "http://apis.baidu.com/apistore/weatherservice/";
    private static final String BASE_URL = "http://192.168.150.50/";

    public static ApiService getDefault() {
        if(SERVICE == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("apikey", "b3ba41f14594acea34b55504803d8aa9");
            HeaderInterceptor headerInterceptor = new HeaderInterceptor(headers);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(headerInterceptor)
                    .build();

            SERVICE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build().create(ApiService.class);
        }

        return SERVICE;
    }
}
