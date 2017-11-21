package com.slava.theapp.model.user.topArtists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.slava.theapp.data.remote.responses.BaseResponse;

public class UserTopArtists extends BaseResponse{

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