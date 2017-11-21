package com.slava.theapp.ui.main.topArtistFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class TopArtistFragmentModule {
    @Provides
    TopArtistsMvp.View provideTopArtistFragmentView(TopArtistsFragment topArtistFragment){
        return topArtistFragment;
    }
}
