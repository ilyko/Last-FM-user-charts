

package com.slava.theapp.ui.base;

import com.slava.theapp.data.DataManager;
import com.slava.theapp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter{

    private static final String TAG = "BasePresenter";

    @Inject
    protected SchedulerProvider schedulerProvider;
    @Inject
    protected CompositeDisposable compositeDisposable;
    @Inject
    protected BasePresenter(){}
}
