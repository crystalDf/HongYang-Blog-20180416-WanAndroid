package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.BaseResponse;
import com.star.wanandroid.core.bean.main.search.UsefulSiteData;

import java.util.List;

public interface UsageDialogContract {

    interface View extends BaseView {

        /**
         * Show useful sites
         *
         * @param usefulSitesResponse BaseResponse<List<UsefulSiteData>>
         */
        void showUsefulSites(BaseResponse<List<UsefulSiteData>> usefulSitesResponse);

        /**
         * Show useful sites data fail
         */
        void showUsefulSitesDataFail();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 常用网站
         */
        void getUsefulSites();
    }
}
