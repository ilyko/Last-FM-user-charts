package com.slava.theapp.di2android;

import com.slava.theapp.ui.hello.HelloActivity;
import com.slava.theapp.ui.hello.HelloMvp;
import com.slava.theapp.ui.main.MainActivity;
import com.slava.theapp.ui.main.MainMvp;
import com.slava.theapp.ui.main.MainPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AndroidBindingModule {
    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract HelloActivity bindHelloActivity();

    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract MainActivity bindMainActivity();

    @Binds
    abstract MainMvp.View bindMainActivity(MainActivity mainActivity);

    @Binds
    abstract HelloMvp.View bindHelloActivity(HelloActivity helloActivity);
}
