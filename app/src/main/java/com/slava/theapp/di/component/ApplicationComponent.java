package com.slava.theapp.di.component;

import android.app.Application;
import android.content.Context;


import com.slava.theapp.MvpApp;
import com.slava.theapp.data.DataManager;
import com.slava.theapp.di.ApplicationContext;
import com.slava.theapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    //void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}