package com.slava.theapp.di2android;

import android.content.Context;

import com.slava.theapp.MvpApp;
import com.slava.theapp.ui.main.MainActivity;
import com.slava.theapp.util.rx.AppRxSchedulers;
import com.slava.theapp.util.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    Context provideContext(MvpApp app){return app.getApplicationContext();}

    @Provides
    SchedulerProvider getSchedulerProvider(){return new AppRxSchedulers();}

}
