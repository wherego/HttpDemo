package com.zhao.httpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhao.httpdemo.Entity.LoginRequest;
import com.zhao.httpdemo.Entity.LoginResponse;
import com.zhao.httpdemo.Entity.TestEntity;
import com.zhao.httpdemo.Entity.WeatherEntity;
import com.zhao.httpdemo.net.Api;
import com.zhao.httpdemo.net.ApiService;
import com.zhao.httpdemo.net.BaseSubscribe;
import com.zhao.httpdemo.Entity.RepoEntity;
import com.zhao.httpdemo.net.ResponseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("123456");
        loginRequest.setPhoneNumber("12345454545");
        loginRequest.setType(1);

        Api.getDefault()
                .login(loginRequest)
                .compose(ResponseHandler.<LoginResponse>handleResult())
                .subscribe(new BaseSubscribe<LoginResponse>(MainActivity.this, "first test...") {
                    @Override
                    protected void _onNext(LoginResponse loginResponse) {
                        Toast.makeText(MainActivity.this, "test er uo ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });

    }
}