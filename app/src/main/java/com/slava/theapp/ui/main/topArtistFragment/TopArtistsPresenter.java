package com.slava.theapp.ui.main.topArtistFragment;


import com.slava.theapp.data.remote.CallbackWrapper;
import com.slava.theapp.model.user.topArtists.UserTopArtists;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;

import javax.inject.Inject;

public class TopArtistsPresenter extends BasePresenter implements TopArtistsMvp.Presenter{

    private int perPage = 30;
    private String user;
    @Inject
    NetworkClient networkClient;
    private TopArtistsMvp.View view;

    @Inject
    public TopArtistsPresenter(TopArtistsMvp.View view){
        this.view = view;
    }

    void setUserId(String user) {
        this.user = user;
    }


    @Override
    public void getTopArtistsByPage(int pageCount, String period) {
        compositeDisposable.add(networkClient
                .getApi()
                .getUserTopArtists(perPage, pageCount, user, period)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribeWith(new CallbackWrapper<UserTopArtists>(view) {
                    @Override
                    protected void onSuccess(UserTopArtists response) {
                        view.handleByPageResponse(response.getTopArtists());
                    }
                }));
    }

    @Override
    public void getFirstPageTopArtist(String period){
        compositeDisposable.add(networkClient
                .getApi()
                .getUserTopArtists(perPage, 1, user, period)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribeWith(new CallbackWrapper<UserTopArtists>(view) {
                    @Override
                    protected void onSuccess(UserTopArtists response) {
                        view.handleFirstPageResponse(response.getTopArtists());
                    }
                }));
    }
}


