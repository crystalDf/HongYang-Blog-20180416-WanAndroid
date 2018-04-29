package com.star.wanandroid.presenter.main;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.main.UsageDialogContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.main.search.UsefulSiteData;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * @author quchao
 * @date 2018/4/2
 */

public class UsageDialogPresenter extends BasePresenter<UsageDialogContract.View> implements UsageDialogContract.Presenter {

    private DataManager mDataManager;

    @Inject
    UsageDialogPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getUsefulSites() {
        addSubscribe(mDataManager.getUsefulSites()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<UsefulSiteData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_useful_sites_data)) {
                    @Override
                    public void onNext(List<UsefulSiteData> usefulSiteDataList) {
                        mView.showUsefulSites(usefulSiteDataList);
                    }
                }));
    }

}
