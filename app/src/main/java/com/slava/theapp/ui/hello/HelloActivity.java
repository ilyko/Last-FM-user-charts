package com.slava.theapp.ui.hello;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.slava.theapp.R;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.util.LogUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class HelloActivity extends BaseActivity implements HelloMvp.View {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    public static int LAYOUT =R.layout.activity_hello;

    @BindView(R.id.text_hello)
    TextView mStatusTextView;

    @Inject
    HelloPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUnBinder(ButterKnife.bind(this));
        presenter.loadMessage();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void setUp() {

    }

    @Override
    public int getLayout() {
        return LAYOUT;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showError(String error) {
        if(!isNetworkConnected()){
            mStatusTextView.setText(error);
        } else {
            mStatusTextView.setText("Connection is OK");}
    }


}
