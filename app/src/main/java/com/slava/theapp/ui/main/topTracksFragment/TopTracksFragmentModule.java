package com.slava.theapp.ui.main.topTracksFragment;

import com.slava.theapp.ui.main.topArtistFragment.TopArtistsFragment;
import com.slava.theapp.ui.main.topArtistFragment.TopArtistsMvp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by slava on 16.11.17.
 */
@Module
public class TopTracksFragmentModule {
    @Provides
    TopArtistsMvp.View provideTopArtistFragmentView(TopArtistsFragment topArtistFragment){
        return topArtistFragment;
    }
}
