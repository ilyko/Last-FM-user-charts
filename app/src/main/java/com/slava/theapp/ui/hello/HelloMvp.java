package com.slava.theapp.ui.hello;

import com.slava.theapp.ui.base.MvpView;

public interface HelloMvp {

    /**
     * Represents the View in MVP.
     */
    interface View extends MvpView {
        void showMessage(String message);

        void openMainActivity(String name);
    }

    /**
     * Represents the Presenter in MVP.
     */
    interface Presenter {
        void getUserInfo(String user);
    }
}