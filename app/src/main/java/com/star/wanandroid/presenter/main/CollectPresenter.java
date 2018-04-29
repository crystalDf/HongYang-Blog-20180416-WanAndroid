package com.star.wanandroid.presenter.main;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.main.CollectContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.star.wanandroid.core.event.CollectEvent;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;

import javax.inject.Inject;

/**
 * @author quchao
 * @date 2018/2/27
 */

public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {

    private DataManager mDataManager;

    @Inject
    CollectPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CollectContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .subscribe(collectEvent -> mView.showRefreshEvent()));
    }

    @Override
    public void getCollectList(int page) {
        addSubscribe(mDataManager.getCollectList(page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_collection_data)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListResponse) {
                        mView.showCollectList(feedArticleListResponse);
                    }
                }));
    }

    @Override
    public void cancelCollectPageArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.cancelCollectPageArticle(feedArticleData.getId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.cancel_collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.setCollect(false);
                        mView.showCancelCollectPageArticleData(position, feedArticleData, feedArticleListData);
                    }
                }));
    }

}
