package com.slava.theapp.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.slava.theapp.R;
import com.slava.theapp.model.Artist;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.ui.base.BaseFragment;
import com.slava.theapp.util.LogUtil;
import com.slava.theapp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class TopArtistsFragment extends BaseFragment implements TopArtistsMvp.View{

    //TODO dagger this:
    protected TopArtistsAdapter topArtistsAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    TopArtistsPresenter presenter;

    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    NetworkClient networkClient;
    @Inject
    SchedulerProvider schedulerProvider;

    @Inject
    public TopArtistsFragment() {
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LogUtil.info(this,"hello: "+presenter);
        topArtistsAdapter = new TopArtistsAdapter(compositeDisposable,schedulerProvider,networkClient);
        mRecyclerView.setAdapter(topArtistsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_top_artist;
    }

    @Override
    public <T extends BaseActivity> T getAct() {
        return null;
    }

    @Override
    public void setArtist(Artist artist) {

    }
}
