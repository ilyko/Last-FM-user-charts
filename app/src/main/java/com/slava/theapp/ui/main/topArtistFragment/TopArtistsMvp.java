package com.slava.theapp.ui.main.topArtistFragment;


import com.slava.theapp.model.user.topArtists.TopArtists;

public interface TopArtistsMvp {
    interface View{
        void handleResponse(TopArtists artist);
        void handleUpdateResponse(TopArtists artists);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopArtists(int perPage, int pageCount);
        void updateTopArtist();
    }

}
