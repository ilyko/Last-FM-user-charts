package com.slava.theapp.di2android;

import android.content.Context;

import com.slava.theapp.database.RealmService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by slava on 24.10.17.
 */
@Module
public class DatabaseModule {
    @Provides
    Realm provideRealm(Context context) {
        Realm.init(context);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name("daggerdemo.realm");
        RealmConfiguration config = builder.build();
        return Realm.getInstance(config);
    }

    @Provides
    RealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }
}
