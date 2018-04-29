package com.star.wanandroid.presenter.main;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.main.SearchListContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.star.wanandroid.core.event.CollectEvent;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;

import javax.inject.Inject;

/**
 * @author quchao
 * @date 2018/3/13
 */

public class SearchListPresenter extends BasePresenter<SearchListContract.View> implements SearchListContract.Presenter {

    private DataManager mDataManager;

    @Inject
    SearchListPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(SearchListContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .filter(collectEvent -> !collectEvent.isCancelCollectSuccess())
                .subscribe(collectEvent -> mView.showCollectSuccess()));

        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .filter(CollectEvent::isCancelCollectSuccess)
                .subscribe(collectEvent -> mView.showCancelCollectSuccess()));
    }

    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }

    @Override
    public void getSearchList(int page, String k) {
        addSubscribe(mDataManager.getSearchList(page, k)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_search_data_list)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showSearchList(feedArticleListData);
                    }
                }));
    }

    @Override
    public void addCollectArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.addCollectArticle(feedArticleData.getId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.setCollect(true);
                        mView.showCollectArticleData(position, feedArticleData, feedArticleListData);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.cancelCollectArticle(feedArticleData.getId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.cancel_collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.setCollect(false);
                        mView.showCancelCollectArticleData(position, feedArticleData, feedArticleListData);
                    }
                }));
    }
}
