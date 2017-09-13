package com.apptopus.gettyimageclient;

import com.apptopus.gettyimageclient.rest.RestService;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ercanozcan on 13/08/17.
 */

public class BaseTest {

    protected MockWebServer mockWebServer;

    protected Retrofit.Builder retrofitBuilder;

    protected RestService restService;

    public void commonSetUp() throws IOException {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public void commonTearDown() throws IOException {
        mockWebServer.shutdown();
        mockWebServer = null;
        retrofitBuilder = null;
        restService = null;
    }
}
