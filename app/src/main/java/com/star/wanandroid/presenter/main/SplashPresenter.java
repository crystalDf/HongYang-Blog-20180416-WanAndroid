package com.star.wanandroid.presenter.main;


import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.main.SplashContract;
import com.star.wanandroid.core.DataManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author quchao
 * @date 2017/11/29
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private DataManager dataManager;

    @Inject
    SplashPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
        long splashTime = 2000;
        addSubscribe(Observable.timer(splashTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> view.jumpToMain()));
    }

}
