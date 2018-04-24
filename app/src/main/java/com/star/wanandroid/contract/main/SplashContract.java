package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;

public interface SplashContract {

    interface View extends BaseView {
        /**
         * after some time jump to main page
         */
        void jumpToMain();
    }

    interface Presenter extends AbstractPresenter<View> {

    }
}
