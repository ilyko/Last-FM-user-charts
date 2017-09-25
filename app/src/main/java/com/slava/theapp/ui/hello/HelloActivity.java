package com.slava.theapp.ui.hello;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.slava.theapp.R;
import com.slava.theapp.di.module.ActivityModule;
import com.slava.theapp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelloActivity extends BaseActivity implements HelloMvp.View {

    @BindView(R.id.text_hello)
    TextView textView;

    @Inject
    HelloMvp.Presenter<HelloMvp.View> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hello);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(HelloActivity.this);
    }
    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showError(String error) {
        if(!isNetworkConnected()){
            textView.setText(error);
        } else {textView.setText("Connection is OK");}
    }


}
