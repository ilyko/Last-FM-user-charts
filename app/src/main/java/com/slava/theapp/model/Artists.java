package com.slava.theapp.model;

/**
 * Created by slava on 02.10.17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Artists {

    @SerializedName("artist")
    @Expose
    private List<Artist> artist = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "artist=" + artist +
                ", attr=" + attr +
                '}';
    }
}
