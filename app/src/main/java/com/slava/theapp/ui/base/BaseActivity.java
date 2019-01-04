package com.slava.theapp.ui.base;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.slava.theapp.util.NetworkUtils;

import butterknife.Unbinder;
import dagger.android.AndroidInjection;


public abstract class BaseActivity extends AppCompatActivity
        implements MvpView {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        closeRealm();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    protected final void addFragment(@IdRes int containerViewId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(containerViewId, fragment)
                .commitAllowingStateLoss();
    }

    protected abstract void closeRealm();

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
    public abstract void onError(String message);

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

    public abstract int getLayout();
}