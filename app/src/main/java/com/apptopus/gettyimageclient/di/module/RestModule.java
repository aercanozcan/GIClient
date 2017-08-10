package com.apptopus.gettyimageclient.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.apptopus.gettyimageclient.GettyImageClientApplication;
import com.apptopus.gettyimageclient.rest.RestService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ercanozcan on 08/08/17.
 */

@Module
public class RestModule {


    private String endPoint;


    public RestModule(String endPoint) {
        this.endPoint = endPoint;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 5 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(GettyImageClientApplication application) {
        return PreferenceManager.getDefaultSharedPreferences(application);//in case access token needed
    }


    @Inject
    @Singleton
    @Provides
    public RestService restService(Retrofit retrofit, Cache cache) {
        return new RestService(retrofit, cache);
    }


}
