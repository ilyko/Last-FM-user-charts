package com.slava.theapp.ui.main;

import android.support.v7.widget.RecyclerView;

import com.slava.theapp.model.Artist;
import com.slava.theapp.ui.base.MvpView;

/**
 * Created by slava on 04.10.17.
 */

public interface TopArtistsMvp {
    interface View{
        void setArtist(Artist artist);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopArtists();
        int getTopArtistsCount();
        void onBindTopArtists(int position, TopArtistsMvp.View holder);
    }

}
