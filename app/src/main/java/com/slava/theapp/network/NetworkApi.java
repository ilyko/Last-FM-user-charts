package com.slava.theapp.network;

import com.slava.theapp.model.Summoner;
import com.slava.theapp.util.Const;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by slava on 27.09.17.
 */

public interface NetworkApi {
    @GET("/na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/RiotSchmick?api_key={api_key}")
    Observable<Summoner> getSummoner(@Path("api_key") String apiKey);
}
