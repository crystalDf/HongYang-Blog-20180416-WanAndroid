package com.star.wanandroid.presenter.project;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.project.ProjectContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.project.ProjectClassifyData;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;


public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    private DataManager mDataManager;

    @Inject
    ProjectPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ProjectContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getProjectClassifyData() {
        addSubscribe(mDataManager.getProjectClassifyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<ProjectClassifyData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_project_classify_data)) {
                    @Override
                    public void onNext(List<ProjectClassifyData> projectClassifyDataList) {
                        mView.showProjectClassifyData(projectClassifyDataList);
                    }
                }));
    }

    @Override
    public int getProjectCurrentPage() {
        return mDataManager.getProjectCurrentPage();
    }

    @Override
    public void setProjectCurrentPage(int page) {
        mDataManager.setProjectCurrentPage(page);
    }

}
