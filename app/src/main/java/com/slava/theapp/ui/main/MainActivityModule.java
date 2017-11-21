package com.slava.theapp.ui.main;


import dagger.Module;
import dagger.Provides;

/**
 * Created by slava on 13.10.17.
 */
@Module
public class MainActivityModule {
    @Provides
    MainMvp.View provideMainView(MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    MainMvp.Presenter provideMainPresenter(MainMvp.View mainView){
        return new MainPresenter(mainView);
    }
}
