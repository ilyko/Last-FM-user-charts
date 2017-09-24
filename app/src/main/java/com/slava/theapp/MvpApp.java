

package com.slava.theapp;

import android.app.Application;


import com.androidnetworking.AndroidNetworking;
import com.slava.theapp.data.DataManager;
import com.slava.theapp.di.component.ApplicationComponent;
import com.slava.theapp.di.component.DaggerApplicationComponent;
import com.slava.theapp.di.module.ApplicationModule;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;

import javax.inject.Inject;

public class MvpApp extends Application {

    @Inject
    DataManager mDataManager;

   /* @Inject
    CalligraphyConfig mCalligraphyConfig;*/

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);


        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.BODY);
        }

        /*CalligraphyConfig.initDefault(mCalligraphyConfig);*/
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
