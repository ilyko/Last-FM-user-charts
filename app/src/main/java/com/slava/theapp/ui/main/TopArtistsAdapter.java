package com.slava.theapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slava.theapp.R;
import com.slava.theapp.model.Artist;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.util.LogUtil;
import com.slava.theapp.util.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by slava on 04.10.17.
 */

public class TopArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Artist> mArtists;
    private int page = 0;
    private boolean isRequest = false;
    NetworkClient networkClient;
    protected SchedulerProvider schedulerProvider;
    protected CompositeDisposable compositeDisposable;

    public TopArtistsAdapter(CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider, NetworkClient networkClient) {
        this.networkClient = networkClient;
        this.compositeDisposable = compositeDisposable;
        this.schedulerProvider = schedulerProvider;
        this.mArtists = new ArrayList<>();
    }


    enum TYPE {EMPTY, ARTIST}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh = null;
        switch (TYPE.values()[viewType]) {
            case EMPTY:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_empty, parent, false);
                vh = new EmptyViewHolder(v);
                vh.itemView.setOnClickListener(null);
                break;
            case ARTIST:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_artist, parent, false);
                vh = new ArtistsViewHolder(v);

        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (!isRequest) {
            return mArtists.size()==0 ? TYPE.EMPTY.ordinal() : TYPE.ARTIST.ordinal();
        } else {
            return position>=mArtists.size() ? TYPE.EMPTY.ordinal() : TYPE.ARTIST.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArtistsViewHolder) {
            ((ArtistsViewHolder)holder).setArtist(mArtists.get(position));
        }

        if (!isRequest && (mArtists.size()==0 || position==mArtists.size()-1)) {
            page++;
            isRequest = true;
            compositeDisposable.add(networkClient
                    .getApi()
                    .getUserTopArtists(30,page)
                    .observeOn(schedulerProvider.ui())
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                            response -> handleResponse(response.getArtists().getArtist()),
                            Throwable::printStackTrace
                    ));
        }
    }

    private void handleResponse(List<Artist> artists) {
        mArtists.addAll(artists);
        LogUtil.info(this,"size:"+mArtists.size());
        notifyDataSetChanged();
        isRequest = false;
    }

    @Override
    public int getItemCount() {
        if (!isRequest) {
            return mArtists.size()==0 ? 1 : mArtists.size();
        } else {
            return mArtists.size()+1;
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
        TextView tvName;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class ArtistsViewHolder extends RecyclerView.ViewHolder implements TopArtistsMvp.View, View.OnClickListener {
        @BindView(R.id.text_name)
        TextView tvName;
        @BindView(R.id.image)
        ImageView imageView;
        public ArtistsViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void setArtist(Artist artist) {
            tvName.setText(getAdapterPosition()+1+": "+artist.getName());
            String path = null;
        for (int i = 0; i < artist.getImage().size(); i++) {
            path = artist.getImage().get(2).getText();
            if (!TextUtils.isEmpty(path)) break;
        }
            if (TextUtils.isEmpty(path)) return;
            Glide.with(tvName.getContext())
                    .load(path)
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            LogUtil.info(this,"clicked");
        }
    }
}


