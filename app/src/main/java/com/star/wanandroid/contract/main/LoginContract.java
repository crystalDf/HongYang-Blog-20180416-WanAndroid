package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.main.login.LoginData;

public interface LoginContract {

    interface View extends BaseView {

        /**
         * Show login data
         *
         * @param loginData LoginData
         */
        void showLoginData(LoginData loginData);

        /**
         * Show register data
         *
         * @param loginData LoginData
         */
        void showRegisterData(LoginData loginData);

        /**
         * Show register fail
         *
         * @param errorMsg error message
         */
        void showRegisterFail(String errorMsg);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Set login status
         *
         * @param account account
         */
        void setLoginAccount(String account);

        /**
         * Set login password
         *
         * @param password password
         */
        void setLoginPassword(String password);

        /**
         * Get Login data
         *
         * @param username user name
         * @param password password
         */
        void getLoginData(String username, String password);

        /**
         * 注册
         * http://www.wanandroid.com/user/register
         *
         * @param username user name
         * @param password password
         * @param rePassword re password
         */
        void getRegisterData(String username, String password, String rePassword);
    }
}
