package com.slava.theapp.model;

/**
 * Created by slava on 01.06.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Similar {

    @SerializedName("artist")
    @Expose
    private List<Artists> artist = null;

    public List<Artists> getArtist() {
        return artist;
    }

    public void setArtist(List<Artists> artist) {
        this.artist = artist;
    }

}
