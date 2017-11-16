package com.slava.theapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.slava.theapp.ui.main.topArtistFragment.TopArtistsFragment;
import com.slava.theapp.ui.main.topTracksFragment.TopTracksFragment;

public class TopListsFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
    TopArtistsFragment topArtistsFragment;
    TopTracksFragment topSongsFragment;
    private String tabTitles[] = new String[]{"Top Artists", "Top Songs", "Top Albums"};

    public TopListsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        topArtistsFragment = TopArtistsFragment.newInstance();
        topSongsFragment = TopTracksFragment.newInstance();
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return topArtistsFragment;
            case 1:
                return topSongsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
