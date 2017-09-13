package com.apptopus.gettyimageclient.rest;

import com.apptopus.gettyimageclient.BaseTest;
import com.apptopus.gettyimageclient.BuildConfig;
import com.apptopus.gettyimageclient.FixtureUtils;
import com.apptopus.gettyimageclient.data.model.GettyResponse;
import com.apptopus.gettyimageclient.data.model.Image;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by ercanozcan on 11/08/17.
 */

public class RestServiceTest extends BaseTest {


    @Before
    public void setUp() throws IOException {
        commonSetUp();
    }

    @Test
    public void successfulResponShouldNotBeNullNorEmpty() throws IOException, InterruptedException {

        Retrofit retrofit = retrofitBuilder.build();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(FixtureUtils.readFixture("successfulResponse.json")));
        restService = new RestService(retrofit);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        restService.gettyService().searchImages("kitties").subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<Response<GettyResponse<Image>>>() {
                    @Override
                    public void onNext(@NonNull Response<GettyResponse<Image>> response) {
                        assertThat("Response code should be 200", response.code(), is(equalTo(200)));
                        assertThat("Response envelope is  null", response.body(), is(notNullValue()));
                        assertThat("Image count is an unexpected value", response.body().getImages().size(), is(equalTo(5)));
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Assert.fail("Unxpected response :" + e.getMessage());
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        countDownLatch.await(2, TimeUnit.SECONDS);
    }


    @Test
    public void requestsShouldHaveApikeyHeader() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HeaderInterceptor())
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        String header = chain.request().header(HeaderInterceptor.HEADER_API_KEY);
                        String expectedHeader = BuildConfig.API_KEY;
                        assertThat("Header does not exist", header, is(notNullValue()));
                        assertThat("Unexpected header", header, is(equalTo(expectedHeader)));
                        return chain.proceed(chain.request());
                    }
                }).build();
        Retrofit retrofit = retrofitBuilder.client(client).build();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(FixtureUtils.readFixture("successfulResponse.json")));
        restService = new RestService(retrofit);
        restService.gettyService().searchImages("kitties").subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<GettyResponse<Image>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<GettyResponse<Image>> response) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @After
    public void tearDown() throws IOException {
        commonTearDown();
    }

}
