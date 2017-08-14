package com.apptopus.gettyimageclient.di.component;


import com.apptopus.gettyimageclient.GettyImageActivity;
import com.apptopus.gettyimageclient.di.module.ApplicationModule;
import com.apptopus.gettyimageclient.di.module.RestModule;
import com.apptopus.gettyimageclient.rest.RestService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ercanozcan on 08/08/17.
 */

@Singleton
@Component(modules = {RestModule.class, ApplicationModule.class})
public interface RestServiceComponent {

    public void inject(GettyImageActivity activity);

    public void inject(RestService restService);

}
