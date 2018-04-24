package com.star.wanandroid.contract.mainpager;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;

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
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         * @param isRefresh If refresh
         */
        void showArticleList(BaseResponse<FeedArticleListData> feedArticleListResponse, boolean isRefresh);

        /**
         * Show collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);

        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);

        /**
         * Show banner data
         *
         * @param bannerResponse BaseResponse<List<BannerData>>
         */
        void showBannerData(BaseResponse<List<BannerData>> bannerResponse);

        /**
         * Show article list fail
         */
        void showArticleListFail();

        /**
         * Show banner data fail
         */
        void showBannerDataFail();

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
