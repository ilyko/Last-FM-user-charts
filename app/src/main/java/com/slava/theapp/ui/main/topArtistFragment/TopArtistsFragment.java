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
import com.slava.theapp.model.user.topArtists.TopArtists;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.ui.base.BaseFragment;
import com.slava.theapp.ui.base.PaginationScrollListener;
import com.slava.theapp.ui.main.TopListsFragmentPagerAdapter;
import com.slava.theapp.util.Const;
import com.slava.theapp.util.LogUtil;
import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class TopArtistsFragment extends BaseFragment implements TopArtistsMvp.View, TopListsFragmentPagerAdapter.PagerAdapterFragments,TopArtistsAdapter.RecyclerViewClickListener {

    public static int LAYOUT = R.layout.fragment_top_artists;

    protected TopArtistsAdapter topArtistsAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @Inject
    TopArtistsPresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;
    private boolean isLastPage;
    private int currentPage = 0;
    private int totalPages = -1;
    private boolean isLoading = true;
    private String period = "overall";

    public static TopArtistsFragment newInstance() {
        Bundle args = new Bundle();
        TopArtistsFragment fragment = new TopArtistsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            period = getArguments().getString("period");
        }
        String user = sharedPreferences.getString(Const.ACTIVE_USER, "");
        initRv();
        presenter.setUserId(user);
        presenter.getFirstPageTopArtist(period);
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
                mRecyclerView.post(() -> topArtistsAdapter.addLoadingFooter());
                presenter.getTopArtistsByPage(currentPage, period);
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
                .subscribe(v -> presenter.getFirstPageTopArtist(period));
        topArtistsAdapter = new TopArtistsAdapter(this);
        mRecyclerView.setAdapter(topArtistsAdapter);
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
    public void handleByPageResponse(TopArtists artists) {
        isLoading = false;
        topArtistsAdapter.handleResponse(artists);
    }

    @Override
    public void handleFirstPageResponse(TopArtists artists) {
        currentPage = Integer.valueOf(artists.getAttr().getPage());
        totalPages = Integer.valueOf(artists.getAttr().getTotalPages());
        isLoading = false;
        isLastPage = currentPage == totalPages;
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

    @Override
    public void updateViewBySpinner(String period) {
        this.period = period;
        presenter.getFirstPageTopArtist(period);
    }
}
