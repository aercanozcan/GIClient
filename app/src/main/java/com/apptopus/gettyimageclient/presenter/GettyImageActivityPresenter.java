package com.apptopus.gettyimageclient.presenter;

import com.apptopus.gettyimageclient.data.model.GettyResponse;
import com.apptopus.gettyimageclient.data.model.Image;
import com.apptopus.gettyimageclient.rest.RestService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ercanozcan on 09/08/17.
 */

public class GettyImageActivityPresenter implements GettyImageActivityContract.Operations {


    RestService restService;

    GettyImageActivityContract.View view;

    public GettyImageActivityPresenter(RestService restService, GettyImageActivityContract.View view) {
        this.restService = restService;
        this.view = view;
    }

    @Override
    public void fetchImages(String phrase, int page, int pageSize) {
        view.showProgress();
        restService.gettyService().searchImages(phrase, page, pageSize).enqueue(new Callback<GettyResponse<Image>>() {
            @Override
            public void onResponse(Call<GettyResponse<Image>> call, Response<GettyResponse<Image>> response) {
                if (response.isSuccessful()) {
                    view.showImages(response.body().getImages());
                } else {
                    try {
                        view.showErrorDialog(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<GettyResponse<Image>> call, Throwable t) {
                view.showErrorDialog(t);
                view.hideProgress();
            }
        });
    }

}
