package com.apptopus.gettyimageclient.di.component;

import com.apptopus.gettyimageclient.GettyImageActivity;
import com.apptopus.gettyimageclient.di.module.ApplicationModule;
import com.apptopus.gettyimageclient.di.module.PersistentDataModule;
import com.apptopus.gettyimageclient.di.module.RestModule;

import dagger.Component;

/**
 * Created by ercanozcan on 08/08/17.
 */

@Component(modules = {PersistentDataModule.class, RestModule.class, ApplicationModule.class})
public interface RestServiceComponent {

    public void inject(GettyImageActivity activity);

}
