package com.apptopus.gettyimageclient.presenter;

import com.apptopus.gettyimageclient.data.model.GettyResponse;
import com.apptopus.gettyimageclient.data.model.Image;
import com.apptopus.gettyimageclient.rest.RestService;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ercanozcan on 09/08/17.
 */

public class GettyImageActivityPresenter implements GettyImageActivityContract.Operations {


    RestService restService;

    GettyImageActivityContract.View view;

    Observable<Response<GettyResponse<Image>>> request;

    public GettyImageActivityPresenter(RestService restService, GettyImageActivityContract.View view) {
        this.restService = restService;
        this.view = view;
    }

    @Override
    public void fetchImages(String phrase, int page, int pageSize) {
        view.showProgress();
        request = restService.gettyService().searchImages(phrase, page, pageSize);
        request.subscribeOn(Schedulers.io())
                .cache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response<GettyResponse<Image>>>() {
                    @Override
                    public void onNext(@NonNull Response<GettyResponse<Image>> response) {
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
                    public void onError(@NonNull Throwable e) {
                        view.showErrorDialog(e);
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
