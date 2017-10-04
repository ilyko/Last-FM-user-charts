package com.slava.theapp.ui.main;

import com.slava.theapp.model.chart.TopArtists;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;
import com.slava.theapp.util.Const;
import com.slava.theapp.util.LogUtil;
import com.slava.theapp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter extends BasePresenter implements MainMvp.Presenter{

    @Inject
    NetworkClient networkClient;

    @Inject
    public MainPresenter(){
    }

    @Inject
    protected SchedulerProvider schedulerProvider;
    @Inject
    protected CompositeDisposable compositeDisposable;

    @Override
    public void getSummoner() {
        compositeDisposable.add(networkClient
                .getApi()
                .getSummoner(Const.API_KEY)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                        //this::handleResponse,
                        //Throwable::printStackTrace
                ));
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


    private void handleResponse(TopArtists response) {
        LogUtil.info(this, "response" + response.toString());
    }
}
