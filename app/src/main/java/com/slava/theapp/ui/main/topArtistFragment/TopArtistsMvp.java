package com.slava.theapp.ui.main.topArtistFragment;


import com.slava.theapp.model.Artist;
import com.slava.theapp.model.Artists;
import com.slava.theapp.model.Attr;


import java.util.List;

/**
 * Created by slava on 04.10.17.
 */

public interface TopArtistsMvp {
    interface View{
        void handleResponse(Artists artist);
        void handleUpdateResponse(Artists artists);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopArtists(int perPage, int pageCount);
        void updateTopArtist();
    }

}
