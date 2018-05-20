package com.star.wanandroid.base.presenter;

import com.star.wanandroid.base.view.AbstractView;

import io.reactivex.disposables.Disposable;

public interface AbstractPresenter<T extends AbstractView> {

    void attachView(T view);

    void detachView();

    void addRxBindingSubscribe(Disposable disposable);

    boolean getNightModeState();

    void setLoginStatus(boolean loginStatus);

    boolean getLoginStatus();

    String getLoginAccount();

    int getCurrentPage();
}
