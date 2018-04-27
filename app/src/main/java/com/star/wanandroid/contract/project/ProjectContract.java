package com.star.wanandroid.contract.project;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.BaseResponse;
import com.star.wanandroid.core.bean.project.ProjectClassifyData;

import java.util.List;

public interface ProjectContract {

    interface View extends BaseView {

        /**
         * Show project classify data
         *
         * @param projectClassifyResponse List<ProjectClassifyData>
         */
        void showProjectClassifyData(BaseResponse<List<ProjectClassifyData>> projectClassifyResponse);

        /**
         * Show project calssify data fail
         */
        void showProjectClassifyDataFail();

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
