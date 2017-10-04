package com.slava.theapp.ui.main;

import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;
import com.slava.theapp.util.LogUtil;
import com.slava.theapp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by slava on 05.10.17.
 */

public class TopArtistsPresenter extends BasePresenter implements TopArtistsMvp.Presenter{
    @Inject
    NetworkClient networkClient;

    @Inject
    public TopArtistsPresenter(){
    }

    @Inject
    protected SchedulerProvider schedulerProvider;
    @Inject
    protected CompositeDisposable compositeDisposable;

    @Override
    public void getTopArtists() {
        compositeDisposable.add(networkClient
                .getApi()
                .getTopArtists(null,null)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                        response -> LogUtil.info(this, "response" + response.toString()),
                        Throwable::printStackTrace
                ));
    }
}
