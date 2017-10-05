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
import com.slava.theapp.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by slava on 04.10.17.
 */

public class TopArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private int page = 0;
    private boolean isRequest = false;
    private final TopArtistsPresenter presenter;

    public TopArtistsAdapter(TopArtistsPresenter presenter) {
        this.presenter = presenter;
        LogUtil.info(this,"hello CONSTRUCTOR");
    }


    enum TYPE {EMPTY, ARTIST}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh = null;
        switch (TYPE.values()[viewType]) {
            case EMPTY:
                LogUtil.info(this,"hello EMPTY");
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_empty, parent, false);
                vh = new EmptyViewHolder(v);
                vh.itemView.setOnClickListener(null);
                break;
            case ARTIST:
                LogUtil.info(this,"hello ARTIST");
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_artist, parent, false);
                vh = new ArtistsViewHolder(v);

        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (!isRequest) {
            return presenter.getTopArtistsCount()==0 ? TYPE.EMPTY.ordinal() : TYPE.ARTIST.ordinal();
        } else {
            return position>=presenter.getTopArtistsCount() ? TYPE.EMPTY.ordinal() : TYPE.ARTIST.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArtistsViewHolder) {
            LogUtil.info(this, "hello ArtistsViewHolder");
            presenter.onBindTopArtists(position, (ArtistsViewHolder) holder);
        }

        LogUtil.info(this, "hello isRequest: "+isRequest+" count"+presenter.getTopArtistsCount());
        if (!isRequest && (presenter.getTopArtistsCount()==0 || position==presenter.getTopArtistsCount()-1)) {
            page++;
            isRequest = true;
            presenter.getTopArtists();
            isRequest = false;
        }
    }

    @Override
    public int getItemCount() {
        if (!isRequest) {
            return presenter.getTopArtistsCount()==0 ? 1 : presenter.getTopArtistsCount();
        } else {
            return presenter.getTopArtistsCount()+1;
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

        }

        @Override
        public void setArtist(Artist artist) {
            tvName.setText(artist.getName());
            String path = null;
        for (int i = 0; i < artist.getImage().size(); i++) {
            path = artist.getImage().get(i).getText();
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


