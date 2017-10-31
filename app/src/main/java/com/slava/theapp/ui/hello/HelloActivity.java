package com.slava.theapp.ui.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.slava.theapp.R;
import com.slava.theapp.database.RealmService;
import com.slava.theapp.model.user.TestUser;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.ui.main.MainActivity;
import com.slava.theapp.util.Const;
import com.slava.theapp.util.LogUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class HelloActivity extends BaseActivity implements HelloMvp.View {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    public static int LAYOUT =R.layout.activity_hello;

    @BindView(R.id.etName)
    TextView etName;
    @BindView(R.id.tv_error_show)
    TextView tvError;
    @BindView(R.id.etPassword)
    TextView etPassword;
    @BindView(R.id.btnLogin)
    Button mButton;
    @Inject
    HelloPresenter presenter;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    RealmService realmService;
    @Inject
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUnBinder(ButterKnife.bind(this));
        loadUserFromSharedPreferences();
        compositeDisposable.add(RxTextView
                .textChanges(etName)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(is -> mButton.setEnabled(is.length()>0))
        );

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.btnLogin)
    public void submit(View view) {
        TestUser testUser = new TestUser();
        testUser.setName(etName.getText().toString());
        testUser.setId(testUser.hashCode());
        realmService.addTestUser(testUser);
        presenter.getUserInfo(testUser.getName());
/*        compositeDisposable.add(realmService
                .getTestUsers()
                .switchMap(Flowable::fromIterable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    LogUtil.info(this, "user:" + user);
                }, throwable -> throwable.printStackTrace()));*/
        //openMainActivity();
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
    protected void closeRealm() {
        realmService.closeRealm();
    }

    @Override
    public void showError(String error) {
        tvError.setText(error);
    }

    @Override
    public void openMainActivity(String user) {
        Intent intent = new Intent(HelloActivity.this, MainActivity.class);
        intent.putExtra(Const.USER_INTENT,user);
        //saveUserToSharedPreferences(user);
        //LogUtil.info(this,"send user: "+etName.getText());
        startActivity(intent);
    }


    void saveUserToSharedPreferences(String activeUser) {
        sharedPreferences.edit().putString(Const.ACTIVE_USER, activeUser).apply();
    }

    void loadUserFromSharedPreferences() {
        String temp = sharedPreferences.getString(Const.ACTIVE_USER, "");
        if(temp.length() > 0){
            Intent intent = new Intent(HelloActivity.this, MainActivity.class);
            intent.putExtra(Const.USER_INTENT,temp);
            startActivity(intent);
        }
    }


    @Override
    public void onUnknownError(String error) {
        LogUtil.info(this, "on Unknown error: "+ error);
    }

    @Override
    public void onTimeout() {
        LogUtil.info(this, "onTimeout error: "+getComponentName());
    }

    @Override
    public void onNetworkError() {
        LogUtil.info(this, "onNetworkError error: "+getComponentName());
    }

    @Override
    public void onConnectionError() {
        LogUtil.info(this, "onConnection error: "+getComponentName());
    }
}
