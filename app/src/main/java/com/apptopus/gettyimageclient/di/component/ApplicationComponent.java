package com.apptopus.gettyimageclient.di.component;

import com.apptopus.gettyimageclient.GettyImageClientApplication;
import com.apptopus.gettyimageclient.di.module.ApplicationModule;

import dagger.Component;

/**
 * Created by ercanozcan on 12/08/17.
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    public void inject(GettyImageClientApplication application);

}
