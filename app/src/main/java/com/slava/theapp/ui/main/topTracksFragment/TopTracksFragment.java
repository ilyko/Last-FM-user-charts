package com.slava.theapp.ui.main.topTracksFragment;

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

import com.slava.theapp.model.user.topTracks.TopTracks;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.ui.base.BaseFragment;
import com.slava.theapp.ui.base.PaginationScrollListener;

import com.slava.theapp.ui.main.TopListsFragmentPagerAdapter;
import com.slava.theapp.util.Const;
import com.slava.theapp.util.LogUtil;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class TopTracksFragment extends BaseFragment implements TopTracksMvp.View, TopListsFragmentPagerAdapter.PagerAdapterFragments, TopTracksAdapter.RecyclerViewClickListener {

    TopTracksAdapter topTracksAdapter;
    public static int LAYOUT = R.layout.fragment_top_tracks;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @Inject
    TopTracksPresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;
    private boolean isLastPage;
    private int currentPage = 0;
    private int totalPages = -1;
    private boolean isLoading = true;
    private String period ="overall";

    //TODO refactor to base generic:
    public static TopTracksFragment newInstance() {
        Bundle args = new Bundle();
        TopTracksFragment fragment = new TopTracksFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String user = sharedPreferences.getString(Const.ACTIVE_USER, "");
        initRv();
        presenter.setUserId(user);
        presenter.getFirstPageTopTracks(period);

    }

    void initRv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        PaginationScrollListener scrollListener = new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                if (currentPage == totalPages) isLastPage = true;
                mRecyclerView.post(() -> topTracksAdapter.addLoadingFooter());
                presenter.getTopTracksByPageNumber(currentPage, period);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);
        RxSwipeRefreshLayout
                .refreshes(swipeContainer)
                .subscribe(v -> presenter.getFirstPageTopTracks(period));
        topTracksAdapter = new TopTracksAdapter(this);
        mRecyclerView.setAdapter(topTracksAdapter);
    }


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
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
    public void recyclerViewListClicked(View v, int position) {
        LogUtil.info(this, "item: " + position + " clicked");
    }


    @Override
    public void handleByPageResponse(TopTracks tracks) {
        isLoading = false;
        topTracksAdapter.handleResponse(tracks);
    }

    @Override
    public void handleFirstPageResponse(TopTracks tracks) {
        currentPage = Integer.valueOf(tracks.getAttr().getPage());
        totalPages = Integer.valueOf(tracks.getAttr().getTotalPages());
        isLoading = false;
        isLastPage = currentPage == totalPages;
        topTracksAdapter.handleUpdateResponse(tracks);
        try {
            RxSwipeRefreshLayout.refreshing(swipeContainer).accept(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateViewBySpinner(String period) {
        this.period = period;
        presenter.getFirstPageTopTracks(period);
    }
}
