package com.slava.theapp.network;

import com.slava.theapp.model.Summoner;
import com.slava.theapp.model.chart.TopArtists;
import com.slava.theapp.model.chart.TopTracks;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {
    @GET("lol/summoner/v3/summoners/by-name/SourceCodeFruc22")
    Observable<Summoner> getSummoner(@Query("api_key") String apiKey);

    @GET("?method=chart.gettopartists")
    Observable<TopArtists> getTopArtists(@Query("page") Integer page,
                                         @Query("limit") Integer limit);

    @GET("?method=chart.gettoptracks")
    Observable<TopTracks> getTopTracks(@Query("page") Integer page,
                                       @Query("limit") Integer limit);
}
