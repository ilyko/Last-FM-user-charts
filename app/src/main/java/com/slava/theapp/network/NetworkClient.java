package com.slava.theapp.network;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by slava on 27.09.17.
 */
@Singleton
public class NetworkClient {
    private Retrofit retrofit;
    private NetworkApi apiHelper;
    private String url;
    private OkHttpClient httpClient;

    @Inject
    public NetworkClient(){init();}

    private void init() {

    }

    public void initBaseUrl(@NonNull String url) throws Exception {
        this.url = "http://www."+url;
        retrofit = new Retrofit.Builder()
                .baseUrl(this.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        apiHelper = retrofit.create(NetworkApi.class);
    }

    public NetworkApi getApi() {return apiHelper;}

    public String getUrl(){ return url;}
}
