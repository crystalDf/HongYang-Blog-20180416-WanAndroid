package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;

public interface CollectContract {

    interface View extends BaseView {

        /**
         * Show collect list
         *
         * @param feedArticleListData FeedArticleListData
         */
        void showCollectList(FeedArticleListData feedArticleListData);

        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCancelCollectPageArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData);

        /**
         * Show Refresh event
         */
        void showRefreshEvent();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get collect list
         *
         * @param page page number
         */
        void getCollectList(int page, boolean isShowError);

        /**
         * Cancel collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectPageArticle(int position, FeedArticleData feedArticleData);


    }
}
