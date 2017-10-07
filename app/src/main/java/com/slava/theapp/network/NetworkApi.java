package com.slava.theapp.network;

import com.slava.theapp.model.Summoner;
import com.slava.theapp.model.chart.TopArtists;
import com.slava.theapp.model.chart.TopTracks;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {
    @GET("lol/summoner/v3/summoners/by-name/SourceCodeFruc22")
    Observable<Summoner> getSummoner(@Query("api_key") String apiKey);

    @GET("?method=chart.gettopartists")
    Observable<TopArtists> getTopArtists(@Query("limit") Integer limit,
                                         @Query("page") Integer page);
    @GET("?method=library.getartists&user=joanofarctan&format=json")
    Observable<TopArtists>  getUserTopArtists(@Query("limit") int limit,
                                              @Query("page") int page);

    @GET("?method=chart.gettoptracks")
    Observable<TopTracks> getTopTracks(@Query("limit") Integer limit,
                                       @Query("page") Integer page);
}
