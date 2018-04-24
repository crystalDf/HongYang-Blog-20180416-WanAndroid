package com.star.wanandroid.contract.hierarchy;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;

public interface KnowledgeHierarchyContract {

    interface View extends BaseView {

        /**
         * Show Knowledge Hierarchy Data
         *
         * @param knowledgeHierarchyResponse BaseResponse<List<KnowledgeHierarchyData>>
         */
        void showKnowledgeHierarchyData(BaseResponse<List<KnowledgeHierarchyData>> knowledgeHierarchyResponse);

        /**
         * Show knowledge hierarchy detail data fail
         */
        void showKnowledgeHierarchyDetailDataFail();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 知识列表
         */
        void getKnowledgeHierarchyData();
    }
}
