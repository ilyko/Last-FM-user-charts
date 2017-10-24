package com.slava.theapp.di2android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.google.gson.Gson;
import com.slava.theapp.MvpApp;
import com.slava.theapp.database.RealmService;
import com.slava.theapp.ui.main.MainActivity;
import com.slava.theapp.util.rx.AppRxSchedulers;
import com.slava.theapp.util.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application app){return app.getApplicationContext();}

    @Provides
    SchedulerProvider getSchedulerProvider(){return new AppRxSchedulers();}

    @Provides
    Gson provideGson(){return new Gson();}

    @Provides
    @Singleton
        // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
    //TODO ?? Realm Service ??
}
