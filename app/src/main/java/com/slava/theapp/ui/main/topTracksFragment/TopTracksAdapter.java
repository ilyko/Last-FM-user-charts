package com.slava.theapp.ui.main.topTracksFragment;


import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slava.theapp.R;
import com.slava.theapp.model.Attr;
import com.slava.theapp.model.Track;
import com.slava.theapp.model.user.topTracks.TopTracks;
import com.slava.theapp.util.LogUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class TopTracksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //TODO how to implement reusable adapter: generic? base adapter? interface instead of data model?
    private List<Track> mTracks;
    private TopTracksAdapter.RecyclerViewClickListener recyclerViewClickListener;
    private Attr attr;
    private boolean isLoadingAdded = false;


    TopTracksAdapter(TopTracksAdapter.RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.mTracks = new ArrayList<>();
    }


    private enum TYPE {LOADING, EMPTY, ARTIST}


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh = null;
        switch (TopTracksAdapter.TYPE.values()[viewType]) {
            case LOADING:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_loading, parent, false);
                vh = new TopTracksAdapter.LoadingViewHolder(v);
                vh.itemView.setOnClickListener(null);
                break;
            case EMPTY:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_empty, parent, false);
                vh = new TopTracksAdapter.EmptyViewHolder(v);
                vh.itemView.setOnClickListener(null);
                break;
            case ARTIST:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_artist, parent, false);
                vh = new TracksViewHolder(v);
                break;
        }
        //LogUtil.info(this, "viewType: "+vh.getClass());
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTracks != null && mTracks.size() > 0) {
            return (position == mTracks.size() - 1 && isLoadingAdded) ? TopTracksAdapter.TYPE.LOADING.ordinal() : TopTracksAdapter.TYPE.ARTIST.ordinal();
            //return mTracks.get(position) == null ? TYPE.LOADING.ordinal() : TYPE.DATA.ordinal();
        } else {
            return TopTracksAdapter.TYPE.EMPTY.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TracksViewHolder) {
            ((TracksViewHolder) holder).setTrack(mTracks.get(position));
        } else if (holder instanceof TopTracksAdapter.LoadingViewHolder) {
            TopTracksAdapter.LoadingViewHolder loadingViewHolder = (TopTracksAdapter.LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return (mTracks == null || mTracks.size() == 0) ? 1 : mTracks.size();

        /* if (!isRequest) {
            return mTracks.size() == 0 ? 1 : mTracks.size();
        } else {
            return mTracks.size()+1;
        }*/
    }

    public Attr getAttr() {
        return attr;
    }

    void addLoadingFooter() {
        if (mTracks != null && mTracks.size() > 0) {
            isLoadingAdded = true;
            mTracks.add(new Track());
            notifyItemInserted(mTracks.size() - 1);
        }
    }

    private void removeLoadingFooter() {
        if (mTracks != null && mTracks.size() > 0) {
            isLoadingAdded = false;

            int position = mTracks.size() - 1;
            Track track = mTracks.get(position);

            if (track != null) {
                mTracks.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    void handleResponse(TopTracks tracks) {
        removeLoadingFooter();
        mTracks.addAll(tracks.getTrack());
        LogUtil.info(this, "size: " + mTracks.size());
        notifyDataSetChanged();
    }

    void handleUpdateResponse(TopTracks tracks) {
        mTracks.clear();
        LogUtil.info(this, "size: " + mTracks.size());
        notifyDataSetChanged();
        mTracks.addAll(tracks.getTrack());
        LogUtil.info(this, "size: " + mTracks.size());
        notifyDataSetChanged();
        attr = tracks.getAttr();
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


    class TracksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.text_name)
        TextView tvName;
        @BindView(R.id.image)
        ImageView imageView;

        TracksViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void setTrack(Track track) {
            tvName.setText(MessageFormat.format("{0}: {1}", getAdapterPosition() + 1, track.getName()));
            tvName.requestLayout();

            String path = null;
            for (int i = 1; i < track.getImage().size(); i++) {
                path = track.getImage().get(i).getText();
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

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View v, int position);
    }
}



