package com.apptopus.gettyimageclient.di.module;

import com.apptopus.gettyimageclient.rest.HeaderInterceptor;
import com.apptopus.gettyimageclient.rest.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addNetworkInterceptor(new HeaderInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public RestService restService(Retrofit retrofit) {
        return new RestService(retrofit);
    }


}
