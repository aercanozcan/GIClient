package com.apptopus.gettyimageclient.rest;

import com.apptopus.gettyimageclient.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ercanozcan on 12/08/17.
 */

public class HeaderInterceptor implements Interceptor {
    public static final String HEADER_API_KEY = "Api-Key";

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
                .addHeader(HEADER_API_KEY, BuildConfig.API_KEY)
                .build());
    }
}
