package com.slava.theapp.ui.main;

import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;

import com.slava.theapp.util.LogUtil;


import javax.inject.Inject;

public class MainPresenter extends BasePresenter implements MainMvp.Presenter{

    @Inject
    NetworkClient networkClient;
    MainMvp.View mainView;

    @Inject
    public MainPresenter(MainMvp.View mainView){
        this.mainView = mainView;
    }

    @Override
    public void getSummoner() {

    }

    @Override
    public void getTopTracks() {
        compositeDisposable.add(networkClient
                .getApi()
                .getTopTracks(null,null)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(response -> LogUtil.info(this, "response" + response.toString()),
                        Throwable::printStackTrace
                ));
    }

}
