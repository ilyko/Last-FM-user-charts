package com.slava.theapp.ui.main;

import com.slava.theapp.ui.base.MvpView;

/**
 * Created by slava on 04.10.17.
 */

public interface TopArtistsMvp {
    interface View{
        void setArtistName(String name);
        void setArtistImage(String path);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopArtists();
    }

}
