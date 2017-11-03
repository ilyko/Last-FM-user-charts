package com.slava.theapp.ui.hello;

import com.slava.theapp.data.remote.CallbackWrapper;
import com.slava.theapp.model.user.UserInfo;
import com.slava.theapp.network.NetworkClient;
import com.slava.theapp.ui.base.BasePresenter;
import com.slava.theapp.util.LogUtil;

import javax.inject.Inject;


public class HelloPresenter extends BasePresenter implements
        HelloMvp.Presenter {

    @Inject
    NetworkClient networkClient;
    private HelloMvp.View view;

    @Inject
    HelloPresenter(HelloMvp.View view) {
        this.view = view;
    }

    @Override
    public void getUserInfo(String user) {
        compositeDisposable.add(networkClient
                .getApi()
                .getUserInfo(user)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribeWith(new CallbackWrapper<UserInfo>(view) {
                    @Override
                    protected void onSuccess(UserInfo userInfoServerResponse) {
                        LogUtil.info(this, "user:" + userInfoServerResponse);
                        view.openMainActivity(userInfoServerResponse.getUser().getName());
                    }
                })
        );
    }

}
