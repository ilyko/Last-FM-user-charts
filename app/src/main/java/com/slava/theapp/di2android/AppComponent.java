package com.slava.theapp.di2android;

import android.app.Application;

import com.slava.theapp.MvpApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                AndroidBindingModule.class
        }
)

public interface AppComponent {


    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(MvpApp mvpApp);
}
