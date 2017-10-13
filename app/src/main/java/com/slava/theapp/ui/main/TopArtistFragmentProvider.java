package com.slava.theapp.ui.main;


import com.slava.theapp.ui.main.topArtistFragment.TopArtistFragmentModule;
import com.slava.theapp.ui.main.topArtistFragment.TopArtistsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by slava on 13.10.17.
 */
@Module
public abstract class TopArtistFragmentProvider {
    @ContributesAndroidInjector(modules = TopArtistFragmentModule.class)
    abstract TopArtistsFragment provideDetailFragmentFactory();
}
