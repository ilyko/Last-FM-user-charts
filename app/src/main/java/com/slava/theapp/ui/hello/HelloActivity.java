package com.slava.theapp.ui.hello;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.slava.theapp.R;
import com.slava.theapp.model.user.TestUser;
import com.slava.theapp.model.user.UserInfo;
import com.slava.theapp.ui.base.BaseActivity;
import com.slava.theapp.ui.main.MainActivity;
import com.slava.theapp.util.Const;
import com.slava.theapp.util.KeyboardUtils;
import com.slava.theapp.util.LogUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class HelloActivity extends BaseActivity implements HelloMvp.View {

    public static int LAYOUT = R.layout.activity_hello;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tv_error_show)
    TextView tvError;
    @BindView(R.id.etPassword)
    TextView etPassword;
    @BindView(R.id.btnLogin)
    Button mButton;
    @BindView(R.id.checkboxLogin)
    CheckBox checkBox;
    ProgressDialog progressDialog;


    @Inject
    HelloPresenter presenter;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Gson gson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUnBinder(ButterKnife.bind(this));
        initProgressDialog();
        loadUserFromSharedPreferences();
        compositeDisposable.add(RxTextView
                .textChanges(etName)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(is -> mButton.setEnabled(is.length() > 0))
        );


    }

    void initProgressDialog(){
        progressDialog = new ProgressDialog(HelloActivity.this,
                R.style.LastFmTheme_Dark_Dialog);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
    }


    @OnClick(R.id.btnLogin)
    public void submit(View view) {
        TestUser testUser = new TestUser();
        testUser.setName(etName.getText().toString().trim());
        testUser.setId(testUser.hashCode());
        progressDialog.show();
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
    public void onError(String message) {
        LogUtil.info(this, "just onError");
        KeyboardUtils.hideSoftKeyboard(this);
        Snackbar.make(mButton, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public int getLayout() {
        return LAYOUT;
    }

    @Override
    public void openMainActivity(UserInfo user) {
        sharedPreferences.edit().putString(Const.ACTIVE_USER, user.getUser().getName()).apply();
        sharedPreferences.edit().putBoolean(Const.REMEMBER_ME, checkBox.isChecked()).apply();
        Intent intent = new Intent(HelloActivity.this, MainActivity.class);
        intent.putExtra(Const.USER_INTENT, gson.toJson(user));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        progressDialog.dismiss();
    }

    void loadUserFromSharedPreferences() {
        String temp = sharedPreferences.getString(Const.ACTIVE_USER, "");
        Boolean isRemember = sharedPreferences.getBoolean(Const.REMEMBER_ME, false);
        checkBox.setChecked(isRemember);
        if (temp.length() > 0 && isRemember) {
            progressDialog.show();
            etName.setText(temp);
            etName.setSelection(temp.length());
            presenter.getUserInfo(temp);
/*            Intent intent = new Intent(HelloActivity.this, MainActivity.class);
            intent.putExtra(Const.USER_INTENT, temp);
            startActivity(intent);*/
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onUnknownError(String error) {
        LogUtil.info(this, "on Unknown error: " + error);
    }

    @Override
    public void onTimeout() {
        LogUtil.info(this, "onTimeout error: " + getComponentName());
    }

    @Override
    public void onNetworkError() {
        LogUtil.info(this, "onNetworkError error: " + getComponentName());
        progressDialog.dismiss();
        Snackbar.make(mButton, "No connection. Check internet connection or try again", Snackbar.LENGTH_LONG)
                .setAction("try again", this::submit)
                .show();
    }

    @Override
    public void onConnectionError() {
        LogUtil.info(this, "onConnection error: " + getComponentName());
    }
}
