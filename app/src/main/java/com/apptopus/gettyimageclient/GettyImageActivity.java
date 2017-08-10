package com.apptopus.gettyimageclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apptopus.gettyimageclient.data.model.GettyResponse;
import com.apptopus.gettyimageclient.data.model.Image;
import com.apptopus.gettyimageclient.di.module.ApplicationModule;
import com.apptopus.gettyimageclient.rest.RestService;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GettyImageActivity extends AppCompatActivity {

    @Inject
    RestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getty_image);
        ((GettyImageClientApplication) getApplication()).restServiceComponent().inject(this);
        restService.gettyService().searchImages("cat").enqueue(new Callback<GettyResponse<Image>>() {
            @Override
            public void onResponse(Call<GettyResponse<Image>> call, Response<GettyResponse<Image>> response) {

                Log.d("img", response.body().toString());
                for (Image image : response.body().getImages()) {
                    Log.d("img", image.getCaption());
                }
            }

            @Override
            public void onFailure(Call<GettyResponse<Image>> call, Throwable t) {

            }
        });
    }
}
