package com.slava.theapp.ui.main.topArtistFragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.slava.theapp.R;
import com.slava.theapp.model.Artists;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.ui.base.BaseFragment;
import com.slava.theapp.ui.base.EndlessRecyclerViewScrollListener;
import com.slava.theapp.util.Const;
import com.slava.theapp.util.LogUtil;
import com.slava.theapp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;

public class TopArtistsFragment extends BaseFragment implements TopArtistsMvp.View, TopArtistsAdapter.RecyclerViewClickListener {

    protected TopArtistsAdapter topArtistsAdapter;
    private int mLoadedItems = 0;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @Inject
    TopArtistsPresenter presenter;

    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    NetworkClient networkClient;
    @Inject
    SchedulerProvider schedulerProvider;
    @Inject
    SharedPreferences sharedPreferences;
    private EndlessRecyclerViewScrollListener scrollListener;

    public static TopArtistsFragment newInstance() {
        Bundle args = new Bundle();
        TopArtistsFragment fragment = new TopArtistsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String user = sharedPreferences.getString(Const.ACTIVE_USER, "");
        presenter.setUserId(user);
        initRv();
    }

    void initRv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getTopArtists(30, page+1);

            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);
        RxSwipeRefreshLayout
                .refreshes(swipeContainer)
                .subscribe(v -> presenter.updateTopArtist());
        topArtistsAdapter = new TopArtistsAdapter(this);
        mRecyclerView.setAdapter(topArtistsAdapter);
    }


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
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
    public void handleResponse(Artists artists){
        topArtistsAdapter.handleResponse(artists);
    }

    @Override
    public void handleUpdateResponse(Artists artists) {
        scrollListener.resetState();
        topArtistsAdapter.handleUpdateResponse(artists);
        try {
            RxSwipeRefreshLayout.refreshing(swipeContainer).accept(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        LogUtil.info(this, "item: " + position + " clicked");
    }
}
