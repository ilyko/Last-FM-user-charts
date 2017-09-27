package com.slava.theapp.ui.main;

import com.slava.theapp.model.Summoner;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;
import com.slava.theapp.util.Const;
import com.slava.theapp.util.LogUtil;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter implements MainMvp.Presenter{

    @Inject
    NetworkClient networkClient;

    @Inject
    public MainPresenter(){}

    @Override
    public void getSummoner(){
        compositeDisposable.add(networkClient
                .getApi()
                .getSummoner(Const.API_KEY)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(this::handleResponse));

    }

    @Override
    public void doNothing(){}

    private void handleResponse(Summoner response) {
        LogUtil.info(this, response.toString());
    }
}
