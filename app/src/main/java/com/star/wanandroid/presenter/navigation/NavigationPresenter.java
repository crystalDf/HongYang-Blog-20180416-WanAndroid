package com.star.wanandroid.presenter.navigation;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.navigation.NavigationContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.navigation.NavigationListData;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * @author quchao
 * @date 2018/2/11
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {

    private DataManager mDataManager;

    @Inject
    NavigationPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(NavigationContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getNavigationListData(boolean isShowError) {
        addSubscribe(mDataManager.getNavigationListData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<NavigationListData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_navigation_list),
                        isShowError) {
                    @Override
                    public void onNext(List<NavigationListData> navigationDataList) {
                        mView.showNavigationListData(navigationDataList);
                    }
                }));
    }

}
