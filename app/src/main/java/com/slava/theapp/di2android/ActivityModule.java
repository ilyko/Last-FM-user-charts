package com.slava.theapp.di2android;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by slava on 27.09.17.
 */
@Module
public class ActivityModule {

    @Provides
    CompositeDisposable provideCompositeDisposable(){return new CompositeDisposable();}

}
