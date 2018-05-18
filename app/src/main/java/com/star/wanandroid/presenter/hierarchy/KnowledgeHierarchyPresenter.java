package com.star.wanandroid.presenter.hierarchy;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.hierarchy.KnowledgeHierarchyContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * @author quchao
 * @date 2017/12/7
 */

public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View>
        implements KnowledgeHierarchyContract.Presenter {

    private DataManager mDataManager;

    @Inject
    KnowledgeHierarchyPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getKnowledgeHierarchyData(boolean isShowError) {
        addSubscribe(mDataManager.getKnowledgeHierarchyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<KnowledgeHierarchyData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(List<KnowledgeHierarchyData> knowledgeHierarchyDataList) {
                        mView.showKnowledgeHierarchyData(knowledgeHierarchyDataList);
                    }
                }));
    }


}
