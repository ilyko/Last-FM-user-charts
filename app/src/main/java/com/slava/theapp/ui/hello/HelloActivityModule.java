package com.slava.theapp.ui.hello;

import dagger.Module;
import dagger.Provides;

@Module
public class HelloActivityModule {
    @Provides
    HelloMvp.View provideHelloView(HelloActivity helloActivity){
        return helloActivity;
    }

    @Provides
    HelloMvp.Presenter provideHelloPresenter(HelloMvp.View helloView){
        return new HelloPresenter(helloView);
    }
}