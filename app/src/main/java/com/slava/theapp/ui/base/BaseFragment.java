package com.slava.theapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by slava on 03.10.17.
 */

public abstract class BaseFragment extends Fragment implements MvpView {
    protected String TAG = "" + getClass().getName();
    private Unbinder unbinder;

    public BaseFragment() {
        setArguments(new Bundle());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public abstract int getLayout();

    public abstract <T extends BaseActivity> T getAct();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveValue(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        restoreValue(getArguments());
    }

    @Override
    public void onPause() {
        saveValue(getArguments());
        super.onPause();

    }

    protected void saveValue(Bundle outState) {
    }

    protected void restoreValue(Bundle outState) {
    }


    public void setBundle(Bundle bundle) {
        Bundle arguments = getArguments();
        arguments.clear();
        arguments.putAll(bundle);
    }

    public void onBack() {
        getAct().getSupportFragmentManager().popBackStack();
    }

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
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void onUnknownError(String error) {

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onConnectionError() {

    }
}
