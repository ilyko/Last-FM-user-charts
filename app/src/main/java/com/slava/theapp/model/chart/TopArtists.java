package com.slava.theapp.model.chart;

/**
 * Created by slava on 02.10.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.slava.theapp.model.Artists;

public class TopArtists {

    @SerializedName("artists")
    @Expose
    private Artists artists;

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "TopArtists{" +
                "artists=" + artists +
                '}';
    }
}
