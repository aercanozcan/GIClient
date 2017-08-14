package com.apptopus.gettyimageclient;

import android.app.Application;
import android.support.annotation.NonNull;

import com.apptopus.gettyimageclient.di.component.DaggerApplicationComponent;
import com.apptopus.gettyimageclient.di.component.DaggerRestServiceComponent;
import com.apptopus.gettyimageclient.di.component.RestServiceComponent;
import com.apptopus.gettyimageclient.di.module.ApplicationModule;
import com.apptopus.gettyimageclient.di.module.RestModule;

/**
 * Created by ercanozcan on 08/08/17.
 */

public class GettyImageClientApplication extends Application {

    private RestServiceComponent restServiceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        restServiceComponent = DaggerRestServiceComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .restModule(new RestModule(BuildConfig.END_POINT))
                .build();

    }

    @NonNull
    protected DaggerApplicationComponent.Builder prepareAppComponent() {
        return DaggerApplicationComponent.builder();
    }

    public RestServiceComponent restServiceComponent() {
        return restServiceComponent;
    }
}
