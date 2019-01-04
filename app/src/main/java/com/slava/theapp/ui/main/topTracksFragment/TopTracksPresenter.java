package com.slava.theapp.ui.main.topTracksFragment;

import com.slava.theapp.data.remote.CallbackWrapper;
import com.slava.theapp.model.user.topTracks.UserTopTracks;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;

import javax.inject.Inject;

public class TopTracksPresenter extends BasePresenter implements TopTracksMvp.Presenter {

    private int perPage = 30;
    private String user;
    @Inject
    NetworkClient networkClient;
    private TopTracksMvp.View view;

    @Inject
    public TopTracksPresenter(TopTracksMvp.View view) {
        this.view = view;
    }

    void setUserId(String user) {
        this.user = user;
    }

    @Override
    public void getTopTracksByPageNumber(int pageCount, String period) {
        compositeDisposable.add(networkClient
                .getApi()
                .getUserTopTracks(perPage, pageCount, user, period)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribeWith(new CallbackWrapper<UserTopTracks>(view) {

                    @Override
                    protected void onSuccess(UserTopTracks userTopTracks) {
                        view.handleByPageResponse(userTopTracks.getTopTracks());
                    }
                }));
    }

    @Override
    public void getFirstPageTopTracks(String period) {
        compositeDisposable.add(networkClient
                .getApi()
                .getUserTopTracks(perPage, 1, user, period)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .doOnSubscribe(disposable -> view.showProgress())
                .subscribeWith(new CallbackWrapper<UserTopTracks>(view) {

                    @Override
                    protected void onSuccess(UserTopTracks userTopTracks) {
                        view.handleFirstPageResponse(userTopTracks.getTopTracks());
                        view.hideProgress();
                    }
                }));
    }
}
