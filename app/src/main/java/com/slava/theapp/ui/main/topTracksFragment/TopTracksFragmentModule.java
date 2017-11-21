package com.slava.theapp.ui.main.topTracksFragment;


import dagger.Module;
import dagger.Provides;

@Module
public class TopTracksFragmentModule {
    @Provides
    TopTracksMvp.View provideTopArtistFragmentView(TopTracksFragment topTracksFragment){
        return topTracksFragment;
    }
}
