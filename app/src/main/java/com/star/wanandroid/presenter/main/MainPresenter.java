package com.star.wanandroid.presenter.main;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.main.MainContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.event.AutoLoginEvent;
import com.star.wanandroid.core.event.LoginEvent;
import com.star.wanandroid.core.event.NightModeEvent;
import com.star.wanandroid.core.event.SwitchNavigationEvent;
import com.star.wanandroid.core.event.SwitchProjectEvent;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseSubscribe;

import javax.inject.Inject;


/**
 * @author quchao
 * @date 2017/11/28
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    MainPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(NightModeEvent.class)
                .compose(RxUtils.rxFlSchedulerHelper())
                .map(NightModeEvent::isNightMode)
                .subscribeWith(new BaseSubscribe<Boolean>(mView, WanAndroidApp.getInstance().getString(R.string.failed_to_cast_mode)) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        registerEvent();
                    }
                })
        );

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin)
                .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(logoutEvent -> mView.showLogoutView()));

        addSubscribe(RxBus.getDefault().toFlowable(AutoLoginEvent.class)
                .subscribe(autoLoginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchProjectEvent.class)
                .subscribe(switchProjectEvent -> mView.showSwitchProject()));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchNavigationEvent.class)
                .subscribe(switchNavigationEvent -> mView.showSwitchNavigation()));
    }


    @Override
    public void setCurrentPage(int page) {
        mDataManager.setCurrentPage(page);
    }

    @Override
    public void setNightModeState(boolean b) {
        mDataManager.setNightModeState(b);
    }
}
