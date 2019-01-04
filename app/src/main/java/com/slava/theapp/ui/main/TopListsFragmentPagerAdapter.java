package com.slava.theapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.slava.theapp.ui.main.topArtistFragment.TopArtistsFragment;
import com.slava.theapp.ui.main.topTracksFragment.TopTracksFragment;

public class TopListsFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 2;
    private PagerAdapterFragments topArtistsFragment;
    private PagerAdapterFragments topSongsFragment;
    private String tabTitles[] = new String[]{"Top Artists", "Top Songs"};

    public TopListsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        topArtistsFragment = TopArtistsFragment.newInstance();
        topSongsFragment = TopTracksFragment.newInstance();
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return(Fragment) topArtistsFragment;
            case 1:
                return (Fragment) topSongsFragment;
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


    public void refreshData(int position) {
        String period;
        switch (position) {
            case 0:
                period = "overall";
                break;
            case 1:
                period = "7day";
                break;
            case 2:
                period = "1month";
                break;
            case 3:
                period = "3month";
                break;
            case 4:
                period = "6month";
                break;
            case 5:
                period = "12month";
                break;
            default:
                period = "overall";

        }
        topArtistsFragment.updateViewBySpinner(period);
        topSongsFragment.updateViewBySpinner(period);
    }

    public interface PagerAdapterFragments {
        void updateViewBySpinner(String period);
    }
}
