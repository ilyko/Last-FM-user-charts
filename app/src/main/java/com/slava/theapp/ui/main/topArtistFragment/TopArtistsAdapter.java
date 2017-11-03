package com.slava.theapp.ui.main.topArtistFragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slava.theapp.R;
import com.slava.theapp.model.Artist;
import com.slava.theapp.model.Artists;
import com.slava.theapp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class TopArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TopArtistsMvp.View {

    private List<Artist> mArtists;
    private int page = 0;
    private boolean isRequest = false;
    private TopArtistsMvp.Presenter presenter;
    private int totalPage = -1;

    TopArtistsAdapter(TopArtistsMvp.Presenter presenter) {
        this.presenter = presenter;
        this.mArtists = new ArrayList<>();
        LogUtil.info(this, "presenter:" + presenter + " this.presenter" + this.presenter);
    }


    private enum TYPE {LOADING, EMPTY, ARTIST}



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh = null;
        switch (TYPE.values()[viewType]) {
            case LOADING:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_loading, parent, false);
                vh = new LoadingViewHolder(v);
                vh.itemView.setOnClickListener(null);
                break;
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
                break;
        }
        //LogUtil.info(this, "viewType: "+vh.getClass());
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (totalPage == 0) return TYPE.EMPTY.ordinal();
        if (!isRequest) {
            return mArtists.size() == 0 ? TYPE.LOADING.ordinal() : TYPE.ARTIST.ordinal();
        } else {
            return position >= mArtists.size() ? TYPE.LOADING.ordinal() : TYPE.ARTIST.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if ((totalPage == -1 || totalPage > page) && !isRequest && (mArtists.size() == 0 || position == mArtists.size() - 1)) {
            page++;
            isRequest = true;
            presenter.getTopArtists(30, page);
        }
        if (holder instanceof ArtistsViewHolder) {
            ((ArtistsViewHolder) holder).setArtist(mArtists.get(position));
        }
    }

    @Override
    public void handleResponse(Artists artists) {
        mArtists.addAll(artists.getArtist());
        LogUtil.info(this, "size: " + mArtists.size());
        totalPage = Integer.valueOf(artists.getAttr().getTotalPages());
        page = Integer.valueOf(artists.getAttr().getPage());
        notifyDataSetChanged();
        isRequest = false;
    }

    @Override
    public void handleUpdateResponse(Artists artists) {
        mArtists.clear();
        LogUtil.info(this, "size: "+mArtists.size());
        notifyDataSetChanged();
        mArtists.addAll(artists.getArtist());
        LogUtil.info(this, "size: " + mArtists.size());
        notifyDataSetChanged();
        isRequest = false;
        totalPage = Integer.valueOf(artists.getAttr().getTotalPages());
        page = Integer.valueOf(artists.getAttr().getPage());
    }

    @Override
    public int getItemCount() {
        if (!isRequest) {
            return mArtists.size() == 0 ? 1 : mArtists.size();
        } else {
            return mArtists.size()+1;
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.no_content)
        TextView tvNoContent;

        EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progress)
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class ArtistsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.text_name)
        TextView tvName;
        @BindView(R.id.image)
        ImageView imageView;

        ArtistsViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void setArtist(Artist artist) {
            tvName.setText(getAdapterPosition() + 1 + ": " + artist.getName());
            tvName.requestLayout();

            String path = null;
            for (int i = 0; i < artist.getImage().size(); i++) {
                path = artist.getImage().get(i).getText();
                if (!TextUtils.isEmpty(path)) break;
            }
            if (TextUtils.isEmpty(path)) {
                imageView.setImageDrawable(null);
                return;
            }
            Glide.with(tvName.getContext())
                    .load(path)
                    .bitmapTransform(new CropCircleTransformation(tvName.getContext()))
                    .into(imageView);
        }


        @Override
        public void onClick(View view) {
            LogUtil.info(this, "clicked");
        }
    }
}


