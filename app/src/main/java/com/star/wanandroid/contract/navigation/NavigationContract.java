package com.star.wanandroid.contract.navigation;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.navigation.NavigationListData;

import java.util.List;

public interface NavigationContract {

    interface View extends BaseView {

        /**
         * Show navigation list data
         *
         * @param navigationDataList List<NavigationListData>
         */
        void showNavigationListData(List<NavigationListData> navigationDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get navigation list data
         */
        void getNavigationListData(boolean isShowError);
    }

}
