package com.apptopus.gettyimageclient.di.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.apptopus.gettyimageclient.GettyImageClientApplication;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by ercanozcan on 08/08/17.
 */

public class PersistentDataModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(GettyImageClientApplication application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
