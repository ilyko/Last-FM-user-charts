package com.slava.theapp.database;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.slava.theapp.model.user.TestUser;
import com.slava.theapp.util.LogUtil;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;


public class RealmService {

    private final Realm realm;

    public RealmService(final Realm realm) {
        this.realm = realm;
    }

    public void closeRealm() {
        realm.close();
    }

    public void addTestUser(TestUser testUser){
        realm.beginTransaction();
        realm.insertOrUpdate(testUser);
        realm.commitTransaction();
    }

    public Flowable<RealmResults<TestUser>> getTestUsers() {
        if(realm.isAutoRefresh()) { // for looper threads. Use `switchMap()`!
            return realm.where(TestUser.class)
                    .findAllAsync()
                    .asFlowable()
                    .filter(RealmResults::isLoaded);
        } else { // for background threads
            return Flowable.just(realm.where(TestUser.class).findAll());
        }
    }

    //other methods
}
