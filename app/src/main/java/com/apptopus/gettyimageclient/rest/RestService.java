package com.apptopus.gettyimageclient.rest;

import com.apptopus.gettyimageclient.data.model.GettyResponse;
import com.apptopus.gettyimageclient.data.model.Image;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ercanozcan on 08/08/17.
 */

public class RestService {


    public static final String VERSION = "/v3";
    public static final String ACTION_IMAGES = "/search/images";


    Retrofit retrofit;

    @Inject
    public RestService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public GettyService gettyService() {
        return retrofit.create(GettyService.class);
    }

    public interface GettyService {


        @GET(VERSION + ACTION_IMAGES)
        Observable<Response<GettyResponse<Image>>> searchImages(@Query("phrase") String phrase);

        @GET(VERSION + ACTION_IMAGES)
        Observable<Response<GettyResponse<Image>>> searchImages(@Query("phrase") String phrase,
                                                                @Query("page") int page,
                                                                @Query("page_size") int pageSize);

    }
}
