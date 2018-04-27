package com.star.wanandroid.contract.navigation;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.BaseResponse;
import com.star.wanandroid.core.bean.navigation.NavigationListData;

import java.util.List;

public interface NavigationContract {

    interface View extends BaseView {

        /**
         * Show navigation list data
         *
         * @param navigationListResponse BaseResponse<List<NavigationListData>>
         */
        void showNavigationListData(BaseResponse<List<NavigationListData>> navigationListResponse);

        /**
         * Show navigation list fail
         */
        void showNavigationListFail();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get navigation list data
         */
        void getNavigationListData();
    }
}
