package com.slava.theapp.ui.main;


import com.slava.theapp.ui.main.topArtistFragment.TopArtistFragmentModule;
import com.slava.theapp.ui.main.topArtistFragment.TopArtistsFragment;
import com.slava.theapp.ui.main.topTracksFragment.TopTracksFragment;
import com.slava.theapp.ui.main.topTracksFragment.TopTracksFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by slava on 13.10.17.
 */
@Module
public abstract class MainActivityFragmentProvider {
    @ContributesAndroidInjector(modules = TopArtistFragmentModule.class)
    abstract TopArtistsFragment provideTopArtistsFragmentFactory();

    @ContributesAndroidInjector(modules = TopTracksFragmentModule.class)
    abstract TopTracksFragment provideTopTracksFragmentFactory();
}
