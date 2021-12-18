package com.slava.theapp.ui.main.topArtistFragment;

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
import com.slava.theapp.model.Artist;
import com.slava.theapp.model.Attr;
import com.slava.theapp.model.user.topArtists.TopArtists;
import com.slava.theapp.util.LogUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class TopArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //TODO how to implement reusable adapter: generic? base adapter? interface instead of data model?
    private List<Artist> mArtists;
    private RecyclerViewClickListener recyclerViewClickListener;
    private Attr attr;
    private boolean isLoadingAdded = false;


    TopArtistsAdapter(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.mArtists = new ArrayList<>();
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
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (mArtists != null && mArtists.size() != 0) {
            return (position == mArtists.size() - 1 && isLoadingAdded) ? TYPE.LOADING.ordinal() : TYPE.ARTIST.ordinal();
        } else {
            return TYPE.EMPTY.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArtistsViewHolder) {
            ((ArtistsViewHolder) holder).setArtist(mArtists.get(position));
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return (mArtists == null || mArtists.size() == 0) ? 1 : mArtists.size();
    }

    public Attr getAttr() {
        return attr;
    }

    void addLoadingFooter() {
        if (mArtists != null && mArtists.size() > 0) {
            isLoadingAdded = true;
            mArtists.add(new Artist());
            notifyItemInserted(mArtists.size() - 1);
        }
    }

    private void removeLoadingFooter() {
        if(mArtists!=null && mArtists.size() > 0) {
            isLoadingAdded = false;

            int position = mArtists.size() - 1;
            Artist artist = mArtists.get(position);

            if (artist != null) {
                mArtists.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    void handleResponse(TopArtists artists) {
        removeLoadingFooter();
        mArtists.addAll(artists.getArtist());
        LogUtil.info(this, "size: " + mArtists.size());
        notifyDataSetChanged();
    }

    void handleUpdateResponse(TopArtists artists) {
        mArtists.clear();
        LogUtil.info(this, "size: " + mArtists.size());
        notifyDataSetChanged();
        mArtists.addAll(artists.getArtist());
        LogUtil.info(this, "size: " + mArtists.size());
        notifyDataSetChanged();
        attr = artists.getAttr();
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
            tvName.setText(MessageFormat.format("{0}: {1}", getAdapterPosition() + 1, artist.getName()));
            tvName.requestLayout();

            String path = null;
            for (int i = 1; i < artist.getImage().size(); i++) {
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

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View v, int position);
    }
}


