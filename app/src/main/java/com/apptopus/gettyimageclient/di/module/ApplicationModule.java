package com.apptopus.gettyimageclient.di.module;

import android.app.Application;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ercanozcan on 08/08/17.
 */

@GlideModule
@Module
public class ApplicationModule extends AppGlideModule {

    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    public ApplicationModule() {
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

}
