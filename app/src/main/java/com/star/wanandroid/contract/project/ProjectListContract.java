package com.star.wanandroid.contract.project;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;

public interface ProjectListContract {

    interface View extends BaseView {

        /**
         * Show project list data
         *
         * @param projectListResponse BaseResponse<ProjectListData>
         */
        void showProjectListData(BaseResponse<ProjectListData> projectListResponse);

        /**
         * Show article list
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCollectOutsideArticle(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);

        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);

        /**
         * Show project list fail
         */
        void showProjectListFail();

        /**
         * Show jump to the top
         */
        void showJumpToTheTop();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get project list data
         *
         * @param page page num
         * @param cid second page id
         */
        void getProjectListData(int page, int cid);

        /**
         * Add collect outside article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void addCollectOutsideArticle(int position, FeedArticleData feedArticleData);

        /**
         * Cancel collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);
    }
}
