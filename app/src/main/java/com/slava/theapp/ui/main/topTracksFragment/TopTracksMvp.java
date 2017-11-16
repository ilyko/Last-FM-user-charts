package com.slava.theapp.ui.main.topTracksFragment;

import com.slava.theapp.model.Artists;

public interface TopTracksMvp {
    interface View{
        void handleByPageResponse(Artists artist);
        void handleFirstPageResponse(Artists artists);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void getTopTracksByPageNumber(int perPage, int pageCount);
        void getFirstPageTopTracks();
    }
}
