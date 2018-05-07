package com.star.wanandroid.presenter.main;

import android.text.TextUtils;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.main.RegisterContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.main.login.LoginData;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getRegisterData(String username, String password, String rePassword) {
        addSubscribe(mDataManager.getRegisterData(username, password, rePassword)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(loginResponse -> !TextUtils.isEmpty(username)
                        && !TextUtils.isEmpty(password)
                        && !TextUtils.isEmpty(rePassword))
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.register_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        mView.showRegisterData(loginData);
                    }
                }));
    }
}
