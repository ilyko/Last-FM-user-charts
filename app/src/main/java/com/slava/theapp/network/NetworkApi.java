package com.slava.theapp.network;

import com.slava.theapp.model.user.UserInfo;
import com.slava.theapp.model.user.topArtists.UserTopArtists;
import com.slava.theapp.model.user.topTracks.UserTopTracks;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("?method=user.gettoptracks")
    Observable<UserTopTracks> getUserTopTracks(@Query("limit") Integer limit,
                                               @Query("page") Integer page,
                                               @Query("user") String user,
                                               @Query("period") String period);


    //user=joanofarctan
    @GET("?method=user.gettopartists")
    Observable<UserTopArtists> getUserTopArtists(@Query("limit") int limit,
                                                 @Query("page") int page,
                                                 @Query("user") String user,
                                                 @Query("period") String period);


    @GET("?method=user.getinfo")
    Observable<UserInfo> getUserInfo(@Query("user") String user);


}
