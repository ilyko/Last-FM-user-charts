package com.slava.theapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slava.theapp.R;
import com.slava.theapp.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by slava on 04.10.17.
 */

public class TopArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private boolean isRequest;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
        public void setArtistName(String name) {
            tvName.setText(name);
        }

        @Override
        public void setArtistImage(String path) {
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


