package com.slava.theapp.ui.main.topTracksFragment;

import com.slava.theapp.model.user.topTracks.TopTracks;
import com.slava.theapp.ui.base.MvpView;

public interface TopTracksMvp {
    interface View extends MvpView{
        void handleByPageResponse(TopTracks tracks);
        void handleFirstPageResponse(TopTracks tracks);
        void showProgress();
        void hideProgress();
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopTracksByPageNumber(int pageCount, String period);
        void getFirstPageTopTracks(String period);
    }
}
