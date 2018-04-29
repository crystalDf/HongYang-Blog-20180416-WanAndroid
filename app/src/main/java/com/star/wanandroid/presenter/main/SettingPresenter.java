package com.star.wanandroid.presenter.main;

import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.main.SettingContract;
import com.star.wanandroid.core.DataManager;

import javax.inject.Inject;

/**
 * @author quchao
 * @date 2018/4/2
 */

public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    private DataManager mDataManager;

    @Inject
    SettingPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }

    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean b) {
        mDataManager.setNightModeState(b);
    }

    @Override
    public void setNoImageState(boolean b) {
        mDataManager.setNoImageState(b);
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mDataManager.setAutoCacheState(b);
    }


}
