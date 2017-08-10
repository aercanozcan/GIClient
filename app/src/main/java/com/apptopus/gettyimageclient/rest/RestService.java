package com.apptopus.gettyimageclient.rest;

import com.apptopus.gettyimageclient.data.model.GettyResponse;
import com.apptopus.gettyimageclient.data.model.Image;

import javax.inject.Inject;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ercanozcan on 08/08/17.
 */

public class RestService {


    public static final String API_KEY = "Api-Key";
    public static final String VERSION = "/V3";
    public static final String ACTION_IMAGES = "/search/images";


    Retrofit retrofit;

    Cache cache;

    @Inject
    public RestService(Retrofit retrofit, Cache cache) {
        this.retrofit = retrofit;
        this.cache = cache;
    }

    public GettyService gettyService() {
        return retrofit.create(GettyService.class);
    }

    public interface GettyService {

        @Headers(API_KEY + ": " + "qemja2tets6c6kkd7vr4v5ak")
        @GET(VERSION + ACTION_IMAGES)
        Call<GettyResponse<Image>> searchImages(@Query("phrase") String phrase);
    }
}
