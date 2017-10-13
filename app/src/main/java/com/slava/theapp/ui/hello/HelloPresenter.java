package com.slava.theapp.ui.hello;

import com.slava.theapp.ui.base.BasePresenter;

import javax.inject.Inject;

public class HelloPresenter extends BasePresenter implements
        HelloMvp.Presenter {



    @Inject
    public HelloPresenter() {
    }


    @Override
    public void loadMessage() {
    }

    @Override
    public void saveName(String firstName, String lastName) {

    }

}
