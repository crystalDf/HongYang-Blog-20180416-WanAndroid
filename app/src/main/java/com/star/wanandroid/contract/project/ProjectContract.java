package com.star.wanandroid.contract.project;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.AbstractView;
import com.star.wanandroid.core.bean.project.ProjectClassifyData;

import java.util.List;

public interface ProjectContract {

    interface View extends AbstractView {

        /**
         * Show project classify data
         *
         * @param projectClassifyDataList List<ProjectClassifyData>
         */
        void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get project classify data
         */
        void getProjectClassifyData();

        /**
         * Get project current page
         *
         * @return project current page
         */
        int getProjectCurrentPage();

        /**
         * Set project current page
         */
        void setProjectCurrentPage(int page);


    }

}
