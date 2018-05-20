package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.AbstractView;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.tbruyelle.rxpermissions2.RxPermissions;

public interface ArticleDetailContract {

    interface View extends AbstractView {

        /**
         * Show collect article data
         *
         * @param feedArticleListData FeedArticleListData
         */
        void showCollectArticleData(FeedArticleListData feedArticleListData);

        /**
         * Show cancel collect article data
         *
         * @param feedArticleListData feedArticleListData
         */
        void showCancelCollectArticleData(FeedArticleListData feedArticleListData);

        /**
         * Share event
         */
        void shareEvent();

        /**
         * Share error
         */
        void shareError();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get auto cache state
         *
         * @return if auto cache state
         */
        boolean getAutoCacheState();

        /**
         * Get no image state
         *
         * @return if has image state
         */
        boolean getNoImageState();

        /**
         * Add collect article
         *
         * @param id article id
         */
        void addCollectArticle(int id);

        /**
         * Cancel collect article
         *
         * @param id article id
         */
        void cancelCollectArticle(int id);

        /**
         * Cancel collect article
         *
         * @param id article id
         */
        void cancelCollectPageArticle(int id);

        /**
         * verify share permission
         *
         * @param rxPermissions RxPermissions
         */
        void shareEventPermissionVerify(RxPermissions rxPermissions);

    }
}
