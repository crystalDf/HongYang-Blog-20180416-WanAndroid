package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.AbstractView;

public interface SplashContract {

    interface View extends AbstractView {
        /**
         * after some time jump to main page
         */
        void jumpToMain();
    }

    interface Presenter extends AbstractPresenter<View> {

    }
}
