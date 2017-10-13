package com.slava.theapp.ui.main.topArtistFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by slava on 13.10.17.
 */

@Module
public class TopArtistFragmentModule {
    @Provides
    TopArtistsMvp.View provideTopArtistFragmentView(TopArtistsFragment topArtistFragment){
        return topArtistFragment;
    }
}
