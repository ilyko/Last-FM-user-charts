package com.slava.theapp.data.remote;

import com.slava.theapp.data.remote.responses.BaseResponse;
import com.slava.theapp.ui.base.MvpView;
import com.slava.theapp.util.LogUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class CallbackWrapper<T extends BaseResponse> extends DisposableObserver<T> {

    private WeakReference<MvpView> weakReference;

    protected CallbackWrapper(MvpView view) {
        this.weakReference = new WeakReference<>(view);
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        //You can return StatusCodes of different cases from your API and handle it here.
        // I usually include these cases on BaseResponse and iherit it from every Response
        if (t.getError() != null) {
            MvpView view = weakReference.get();
            view.onError(t.getMessage());
        } else {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.info(this, "error: " + e);
        MvpView view = weakReference.get();
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            view.onUnknownError(getErrorMessage(responseBody));
        } else if (e instanceof SocketTimeoutException) {
            view.onTimeout();
        } else if (e instanceof IOException) {
            view.onNetworkError();
        } else {
            view.onUnknownError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
