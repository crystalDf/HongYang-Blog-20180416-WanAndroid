package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.AbstractView;
import com.star.wanandroid.core.bean.main.search.UsefulSiteData;

import java.util.List;

public interface UsageDialogContract {

    interface View extends AbstractView {

        /**
         * Show useful sites
         *
         * @param usefulSiteDataList List<UsefulSiteData>
         */
        void showUsefulSites(List<UsefulSiteData> usefulSiteDataList);
    }

    interface Presenter extends AbstractPresenter<UsageDialogContract.View> {

        /**
         * 常用网站
         */
        void getUsefulSites();
    }

}
