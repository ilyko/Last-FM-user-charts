package com.slava.theapp.model.user.topTracks;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;
        import com.slava.theapp.data.remote.responses.BaseResponse;

public class UserTopTracks extends BaseResponse{

    @SerializedName("toptracks")
    @Expose
    private TopTracks topTracks;

    public TopTracks getTopTracks() {
        return topTracks;
    }

    public void setToptracks(TopTracks topTracks) {
        this.topTracks = topTracks;
    }

}
