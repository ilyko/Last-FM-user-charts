package com.slava.theapp.di2android;

import com.slava.theapp.ui.hello.HelloActivity;
import com.slava.theapp.ui.hello.HelloActivityModule;
import com.slava.theapp.ui.main.MainActivity;
import com.slava.theapp.ui.main.MainActivityModule;
import com.slava.theapp.ui.main.TopArtistFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AndroidBindingModule {

    @ContributesAndroidInjector(modules = {ActivityModule.class, HelloActivityModule.class})
    abstract HelloActivity bindHelloActivity();

    @ContributesAndroidInjector(modules = {ActivityModule.class, MainActivityModule.class, TopArtistFragmentProvider.class})
    abstract MainActivity bindMainActivity();

/*    @Binds
    abstract MainMvp.View bindMainActivity(MainActivity mainActivity);

    @Binds
    abstract HelloMvp.View bindHelloActivity(HelloActivity helloActivity);

    @Binds
    abstract TopArtistsMvp.View bindTopArtistsFragment(TopArtistsFragment topArtistsFragment);*/
}
