package com.slava.theapp.ui.hello;

import com.slava.theapp.data.DataManager;
import com.slava.theapp.model.Person;
import com.slava.theapp.ui.base.BasePresenter;
import com.slava.theapp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HelloPresenter<V extends HelloMvp.View> extends BasePresenter<V> implements
        HelloMvp.Presenter<V>  {

    private Person person;
    private HelloMvp.View view;

    @Inject
    public HelloPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void loadMessage() {

    }

    @Override
    public void saveName(String firstName, String lastName) {

    }

}
