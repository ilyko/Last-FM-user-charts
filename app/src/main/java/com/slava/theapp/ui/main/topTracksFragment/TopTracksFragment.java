package com.slava.theapp.ui.main.topTracksFragment;

import android.os.Bundle;

import com.slava.theapp.R;
import com.slava.theapp.model.Artists;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.ui.base.BaseFragment;

/**
 * Created by slava on 16.11.17.
 */

public class TopTracksFragment extends BaseFragment implements TopTracksMvp.View{

    public static int LAYOUT = R.layout.fragment_top_tracks;
    //TODO refactor to base generic:
    public static TopTracksFragment newInstance() {
        Bundle args = new Bundle();
        TopTracksFragment fragment = new TopTracksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return LAYOUT;
    }

    @Override
    public <T extends BaseActivity> T getAct() {
        return null;
    }

    @Override
    public void handleByPageResponse(Artists artist) {

    }

    @Override
    public void handleFirstPageResponse(Artists artists) {

    }
}
