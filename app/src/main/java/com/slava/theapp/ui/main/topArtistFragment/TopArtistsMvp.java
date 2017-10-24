package com.slava.theapp.ui.main.topArtistFragment;


import com.slava.theapp.model.Artist;


import java.util.List;

/**
 * Created by slava on 04.10.17.
 */

public interface TopArtistsMvp {
    interface View{
        void setArtist(Artist artist);
        void handleResponse(List<Artist> artists);
        void handleUpdateResponse(List<Artist> artists);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopArtists(int perPage, int pageCount);
        void updateTopArtist();
    }

}
