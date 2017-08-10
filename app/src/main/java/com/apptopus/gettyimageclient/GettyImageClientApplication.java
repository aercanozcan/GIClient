package com.apptopus.gettyimageclient;

import android.app.Application;

import com.apptopus.gettyimageclient.di.component.DaggerRestServiceComponent;
import com.apptopus.gettyimageclient.di.component.RestServiceComponent;
import com.apptopus.gettyimageclient.di.module.ApplicationModule;
import com.apptopus.gettyimageclient.di.module.RestModule;

/**
 * Created by ercanozcan on 08/08/17.
 */

public class GettyImageClientApplication extends Application{

    private RestServiceComponent restServiceComponent;
    private String END_POINT = "https://api.gettyimages.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        restServiceComponent = DaggerRestServiceComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .restModule(new RestModule(END_POINT))
                .build();

    }

    public RestServiceComponent restServiceComponent() {
        return restServiceComponent;
    }
}
