package com.star.wanandroid.contract.hierarchy;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;

import java.util.List;

public interface KnowledgeHierarchyContract {

    interface View extends BaseView {

        /**
         * Show Knowledge Hierarchy Data
         *
         * @param knowledgeHierarchyDataList (List<KnowledgeHierarchyData>
         */
        void showKnowledgeHierarchyData(List<KnowledgeHierarchyData> knowledgeHierarchyDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 知识列表
         */
        void getKnowledgeHierarchyData(boolean isShowError);
    }
}
