package com.star.wanandroid.contract.mainpager;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.main.banner.BannerData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;

import java.util.List;

public interface MainPagerContract {

    interface View extends BaseView {

        /**
         * Show auto login success
         */
        void showAutoLoginSuccess();

        /**
         * Show auto login fail
         */
        void showAutoLoginFail();

        /**
         * Show content
         *
         * @param feedArticleListData FeedArticleListData
         * @param isRefresh If refresh
         */
        void showArticleList(FeedArticleListData feedArticleListData, boolean isRefresh);

        /**
         * Show collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData);

        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData);

        /**
         * Show banner data
         *
         * @param bannerDataList List<BannerData>
         */
        void showBannerData(List<BannerData> bannerDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get login password
         *
         * @return login password
         */
        String getLoginPassword();

        /**
         * Load main pager data
         */
        void loadMainPagerData();

        /**
         * Get feed article list
         */
        void getFeedArticleList();

        /**
         * Add collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void addCollectArticle(int position, FeedArticleData feedArticleData);

        /**
         * Cancel collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);

        /**
         * Get banner data
         */
        void getBannerData();

        /**
         * Auto refresh
         */
        void autoRefresh();

        /**
         * Load more
         */
        void loadMore();

    }

}
