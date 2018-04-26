package com.star.wanandroid.contract.hierarchy;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.BaseResponse;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;

public interface KnowledgeHierarchyListContract {

    interface View extends BaseView {

        /**
         * Show Knowledge Hierarchy Detail Data
         *
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showKnowledgeHierarchyDetailData(BaseResponse<FeedArticleListData> feedArticleListResponse);

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
         * Show knowledge hierarchy detail data fail
         */
        void showKnowledgeHierarchyDetailDataFail();

        /**
         * Show jump the top
         */
        void showJumpTheTop();

        /**
         * Show reload detail event
         */
        void showReloadDetailEvent();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 知识列表
         *
         * @param page page num
         * @param cid second page id
         */
        void getKnowledgeHierarchyDetailData(int page, int cid);

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
    }
}
