package com.slava.theapp.ui.base;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.slava.theapp.MvpApp;
import com.slava.theapp.di.component.ActivityComponent;
import com.slava.theapp.di.component.DaggerActivityComponent;
import com.slava.theapp.di.module.ActivityModule;
import com.slava.theapp.util.NetworkUtils;

import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity
        implements MvpView {

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract void setUp();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(@StringRes int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(@StringRes int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkAvailable(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {

    }
}
