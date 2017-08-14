package com.apptopus.gettyimageclient;

import com.apptopus.gettyimageclient.rest.RestService;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ercanozcan on 13/08/17.
 */

public class BaseTest {

    protected MockWebServer mockWebServer;

    protected Retrofit.Builder retrofitBuilder;

    protected RestService restService;

    public void commonSetUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public void commonTearDown() throws IOException {
        mockWebServer.shutdown();
        mockWebServer = null;
        retrofitBuilder = null;
        restService = null;
    }
}
