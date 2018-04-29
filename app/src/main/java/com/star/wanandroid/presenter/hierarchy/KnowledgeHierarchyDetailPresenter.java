package com.star.wanandroid.presenter.hierarchy;

import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.hierarchy.KnowledgeHierarchyDetailContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.event.SwitchNavigationEvent;
import com.star.wanandroid.core.event.SwitchProjectEvent;

import javax.inject.Inject;

/**
 * @author quchao
 * @date 2018/2/23
 */

public class KnowledgeHierarchyDetailPresenter extends BasePresenter<KnowledgeHierarchyDetailContract.View>
        implements KnowledgeHierarchyDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    KnowledgeHierarchyDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(KnowledgeHierarchyDetailContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(SwitchProjectEvent.class)
                .subscribe(switchProjectEvent -> mView.showSwitchProject()));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchNavigationEvent.class)
                .subscribe(switchNavigationEvent -> mView.showSwitchNavigation()));
    }


}
