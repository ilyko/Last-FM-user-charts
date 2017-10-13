package com.slava.theapp.network;

import com.slava.theapp.model.Summoner;
import com.slava.theapp.model.chart.TopArtists;
import com.slava.theapp.model.chart.TopTracks;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("?method=chart.gettopartists")
    Observable<TopArtists> getTopArtists(@Query("limit") Integer limit,
                                         @Query("page") Integer page);

    //user=joanofarctan
    @GET("?method=library.getartists&format=json")
    Observable<TopArtists>  getUserTopArtists(@Query("limit") int limit,
                                              @Query("page") int page,
                                              @Query("user") String user);

    @GET("?method=chart.gettoptracks")
    Observable<TopTracks> getTopTracks(@Query("limit") Integer limit,
                                       @Query("page") Integer page);

/*    @POST("?method=auth.getMobileSession")
    Observable<TopTracks> getMobileSession(@Query("username") String username,
                                       @Query("password") String password,
                                       @Query("api_key") String api_key,
                                       @Query("api_sig") String api_sig);*/
}
