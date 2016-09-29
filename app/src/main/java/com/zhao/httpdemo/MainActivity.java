package com.zhao.httpdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhao.httpdemo.entity.LoginRequest;
import com.zhao.httpdemo.entity.LoginResponse;
import com.zhao.httpdemo.net.Api;
import com.zhao.httpdemo.net.ApiService;
import com.zhao.httpdemo.net.BaseSubscribe;
import com.zhao.httpdemo.entity.RepoEntity;
import com.zhao.httpdemo.net.ResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.测试同步调用
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                testHttpSync();
//            }
//        }.start();

        //2.测试异步调用
        //       testHttpAsync();

//        3.测试Rxjava
        testRxjava();

    }

    //同步调用
    private void testHttpSync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<RepoEntity>> repos = service.listRepos("octocat");

        // 同步调用
        try {
            List<RepoEntity> data = repos.execute().body();
            for (RepoEntity d : data) {
                System.out.println(d.getFullName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //2.异步调用
    private void testHttpAsync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<RepoEntity>> repos = service.listRepos("octocat");

        repos.enqueue(new Callback<List<RepoEntity>>() {
            @Override
            public void onResponse(Call<List<RepoEntity>> call, Response<List<RepoEntity>> response) {
                List<RepoEntity> data = response.body();
                for (RepoEntity d : data) {
                    System.out.println(d.getFullName() + "+++++++##Async##++++++");
                }
            }

            @Override
            public void onFailure(Call<List<RepoEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void testRxjava() {

/*                Api.getDefault()
                        .test("武汉")
                        .compose(ResponseHandler.<ArrayList<WeatherEntity>>handleResult())
                        .subscribe(new BaseSubscribe<ArrayList<WeatherEntity>>(MainActivity.this, "正在查询...") {
                            @Override
                            protected void _onNext(ArrayList<WeatherEntity> weatherEntities) {
                                for(WeatherEntity weatherEntity : weatherEntities)
                                    Toast.makeText(MainActivity.this,weatherEntity.toString(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            protected void _onError(String message) {
                                Toast.makeText(MainActivity.this,message, Toast.LENGTH_LONG).show();
                            }
                        });
*/

/*        Api.getDefault()
                .test()
                .compose(ResponseHandler.<TestEntity>handleResult())
                .subscribe(new BaseSubscribe<TestEntity>(MainActivity.this, "first test...") {
                    @Override
                    protected void _onNext(TestEntity testEntity) {
                        Toast.makeText(MainActivity.this, testEntity.getUsername(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
*/


//        登录接口test
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setPassword("123456");
//        loginRequest.setPhoneNumber("12345454545");
//        loginRequest.setType(1);
//
//        Api.getDefault()
//                .login(loginRequest)
//                .compose(ResponseHandler.<LoginResponse>handleResult())
//                .subscribe(new BaseSubscribe<LoginResponse>(MainActivity.this, "loading...") {
//                    @Override
//                    protected void _onNext(LoginResponse loginResponse) {
//                        Toast.makeText(MainActivity.this, "test er uo ", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    protected void _onError(String message) {
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
//                    }
//                });



        Api.getDefault()
                .login("{password\":\"12345\",\"phone_number\":\"12345454545\",\"type\":1}")
                .compose(ResponseHandler.<LoginResponse>handleResult())
                .subscribe(new BaseSubscribe<LoginResponse>(MainActivity.this, "loading...") {
                    @Override
                    protected void _onNext(LoginResponse loginResponse) {
                        Toast.makeText(MainActivity.this, "test er uo ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });

/*     单文件上传

        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Pictures/test111.png");
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part1 =
                MultipartBody.Part.createFormData("zwfile", "zwfilename.png", requestBody);

        Api.getDefault()
                .uploadFile(part1)
                .compose(ResponseHandler.<ResponseBody>handleResult())
                .subscribe(new BaseSubscribe<ResponseBody>(MainActivity.this, "first test...") {
                    @Override
                    protected void _onNext(ResponseBody responseBody) {
                        Toast.makeText(MainActivity.this, "test er uo ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });

*/

        /*     多文件上传*/
/*

        File file1 = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Pictures/test111.png");
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/png"), file1);
        MultipartBody.Part part1 = MultipartBody.Part.createFormData("zwfile", "zwfilename.png", requestBody1);

        File file2 = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Pictures/zw.txt");
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/plain"), file2);
        MultipartBody.Part part2 = MultipartBody.Part.createFormData("zwtesttxt", "zw11.txt", requestBody2);

        File file3 = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Pictures/eb.txt");
        RequestBody requestBody3 = RequestBody.create(MediaType.parse("text/plain"), file3);
        MultipartBody.Part part3 = MultipartBody.Part.createFormData("ebtxt", "eb1.txt", requestBody3);

        List<MultipartBody.Part>  parts = new ArrayList<MultipartBody.Part>();
        parts.add(part1);
        parts.add(part2);
        parts.add(part3);

        Api.getDefault()
                .uploadFiles(parts)
                .compose(ResponseHandler.<ResponseBody>handleResult())
                .subscribe(new BaseSubscribe<ResponseBody>(MainActivity.this, "first test...") {
                    @Override
                    protected void _onNext(ResponseBody responseBody) {
                        Toast.makeText(MainActivity.this, "test er uo ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
*/

    }
}