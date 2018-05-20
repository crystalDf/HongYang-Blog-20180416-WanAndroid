package com.star.wanandroid.contract.hierarchy;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.AbstractView;

public interface KnowledgeHierarchyDetailContract {

    interface View extends AbstractView {

        /**
         * Show switch project
         */
        void showSwitchProject();

        /**
         * Show switch navigation
         */
        void showSwitchNavigation();
    }

    interface Presenter extends AbstractPresenter<View> {


    }
}
