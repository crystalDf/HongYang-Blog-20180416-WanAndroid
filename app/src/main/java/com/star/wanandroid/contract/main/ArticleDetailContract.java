package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.BaseResponse;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.tbruyelle.rxpermissions2.RxPermissions;

public interface ArticleDetailContract {

    interface View extends BaseView {

        /**
         * Show collect article data
         *
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCollectArticleData(BaseResponse<FeedArticleListData> feedArticleListResponse);

        /**
         * Show cancel collect article data
         *
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCancelCollectArticleData(BaseResponse<FeedArticleListData> feedArticleListResponse);

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
