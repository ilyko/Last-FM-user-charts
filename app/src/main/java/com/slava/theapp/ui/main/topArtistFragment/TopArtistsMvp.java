package com.slava.theapp.ui.main.topArtistFragment;


import com.slava.theapp.model.user.topArtists.TopArtists;
import com.slava.theapp.ui.base.MvpView;

public interface TopArtistsMvp{
    interface View extends MvpView{
        void handleByPageResponse(TopArtists artist);
        void handleFirstPageResponse(TopArtists artists);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopArtistsByPage(int pageCount, String period);
        void getFirstPageTopArtist(String period);
    }

}
