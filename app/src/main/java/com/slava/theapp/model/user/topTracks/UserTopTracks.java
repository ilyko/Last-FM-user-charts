package com.slava.theapp.model.user.topTracks;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class UserTopTracks {

    @SerializedName("toptracks")
    @Expose
    private TopTracks toptracks;

    public TopTracks getToptracks() {
        return toptracks;
    }

    public void setToptracks(TopTracks toptracks) {
        this.toptracks = toptracks;
    }

}
