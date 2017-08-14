package com.apptopus.gettyimageclient.di.module;

import com.apptopus.gettyimageclient.presenter.GettyImageActivityContract;
import com.apptopus.gettyimageclient.presenter.GettyImageActivityPresenter;
import com.apptopus.gettyimageclient.rest.RestService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ercanozcan on 13/08/17.
 */

@Module
public class GettyImageActivityModule {


    private GettyImageActivityContract.View view;
    private RestService restService;

    public GettyImageActivityModule(RestService restService, GettyImageActivityContract.View view) {
        this.view = view;
        this.restService = restService;
    }

    @Provides
    public GettyImageActivityContract.View provideView() {
        return view;
    }

    @Provides
    public GettyImageActivityPresenter providePresenter() {
        return new GettyImageActivityPresenter(restService, view);
    }

}
