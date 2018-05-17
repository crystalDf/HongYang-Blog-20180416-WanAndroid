package com.star.wanandroid.base.view;

public interface BaseView {

    void useNightMode(boolean isNightMode);

    void showErrorMsg(String errorMsg);

    void showNormal();

    void showError();

    void showLoading();

    void reload();

    void showLoginView();

    void showLogoutView();

    void showCollectSuccess();

    void showCancelCollectSuccess();
}
