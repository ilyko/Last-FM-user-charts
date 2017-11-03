package com.slava.theapp.network;

import android.support.annotation.NonNull;

import com.slava.theapp.BuildConfig;
import com.slava.theapp.util.LogUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class NetworkClient {
    private Retrofit retrofit;
    private NetworkApi apiHelper;
    private String url;
    private OkHttpClient httpClient;

    @Inject
    public NetworkClient() {
        try {
            initBaseUrl(BuildConfig.API_ENDPOINT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBaseUrl(@NonNull String url) throws Exception {
        this.url = url;
        httpClient = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url1 = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .addQueryParameter("format", "json")
                            .build();
                    LogUtil.info(this, "url1: " + url1);

                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url1);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(this.url)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiHelper = retrofit.create(NetworkApi.class);
    }


    public NetworkApi getApi() {
        return apiHelper;
    }

    public String getUrl() {
        return url;
    }

}
