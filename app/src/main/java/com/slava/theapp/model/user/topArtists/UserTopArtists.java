package com.slava.theapp.model.user.topArtists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTopArtists {

    @SerializedName("topartists")
    @Expose
    private TopArtists topArtists;

    public TopArtists getTopArtists() {
        return topArtists;
    }

    public void setTopartists(TopArtists topArtists) {
        this.topArtists = topArtists;
    }

}