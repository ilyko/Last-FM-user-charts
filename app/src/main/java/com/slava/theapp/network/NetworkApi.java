package com.slava.theapp.network;

import com.slava.theapp.data.remote.responses.ServerResponse;
import com.slava.theapp.model.chart.TopArtists;
import com.slava.theapp.model.chart.TopTracks;
import com.slava.theapp.model.user.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("?method=chart.gettopartists")
    Observable<TopArtists> getTopArtists(@Query("limit") Integer limit,
                                         @Query("page") Integer page);

    //user=joanofarctan
    @GET("?method=library.getartists")
    Observable<TopArtists>  getUserTopArtists(@Query("limit") int limit,
                                              @Query("page") int page,
                                              @Query("user") String user);

    @GET("?method=chart.gettoptracks")
    Observable<TopTracks> getTopTracks(@Query("limit") Integer limit,
                                       @Query("page") Integer page);

    @GET("?method=user.getinfo")
    Observable<UserInfo> getUserInfo(@Query("user") String user);


}
