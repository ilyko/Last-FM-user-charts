package com.slava.theapp.model.chart;

/**
 * Created by slava on 03.10.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.slava.theapp.model.Tracks;

public class TopTracks {

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "TopTracks{" +
                "tracks=" + tracks +
                '}';
    }
}