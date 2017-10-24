package com.slava.theapp.ui.main.topArtistFragment;


import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by slava on 05.10.17.
 */

public class TopArtistsPresenter extends BasePresenter implements TopArtistsMvp.Presenter{


    private String user;
    @Inject
    NetworkClient networkClient;
    TopArtistsMvp.View view;

    @Inject
    public TopArtistsPresenter(TopArtistsMvp.View view){
        this.view = view;
    }

    public void setUserId(String user) {
        this.user = user;
    }


    @Override
    public void getTopArtists(int perPage, int pageCount) {
        compositeDisposable.add(networkClient
                .getApi()
                .getUserTopArtists(perPage, pageCount, user)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                        response -> view.handleResponse(response.getArtists().getArtist()),// handleResponse(response.getArtists().getArtist()),
                        Throwable::printStackTrace
                ));
    }

    @Override
    public void updateTopArtist(){
        compositeDisposable.add(networkClient
                .getApi()
                .getUserTopArtists(30, 1, user)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                        response -> view.handleUpdateResponse(response.getArtists().getArtist()),// handleResponse(response.getArtists().getArtist()),
                        Throwable::printStackTrace
                ));
    }
}


