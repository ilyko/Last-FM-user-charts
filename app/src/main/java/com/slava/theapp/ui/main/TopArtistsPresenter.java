package com.slava.theapp.ui.main;


import com.slava.theapp.model.Artist;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;
import com.slava.theapp.util.LogUtil;
import com.slava.theapp.util.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by slava on 05.10.17.
 */

public class TopArtistsPresenter extends BasePresenter implements TopArtistsMvp.Presenter{


    private List<Artist> topArtists = new ArrayList<>();

    @Inject
    NetworkClient networkClient;

    @Inject
    public TopArtistsPresenter(){
    }

    @Override
    public void getTopArtists() {
        LogUtil.info(this,"hello");
        compositeDisposable.add(networkClient
                .getApi()
                .getTopArtists(null,null)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                        response -> handleResponse(response.getArtists().getArtist()),
                        Throwable::printStackTrace
                ));
    }

    private void handleResponse(List<Artist> topArtists) {
        this.topArtists = topArtists;
        LogUtil.info(this, "hello Response: "+this.topArtists.size());
    }

    @Override
    public int getTopArtistsCount() {
        LogUtil.info(this, "hello SIZE: "+topArtists.size());
        return topArtists.size();

    }

    @Override
    public void onBindTopArtists(int position, TopArtistsMvp.View view) {
        Artist artist = topArtists.get(position);
        view.setArtist(artist);
    }
}
