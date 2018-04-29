package com.star.wanandroid.presenter.main;

import android.Manifest;

import com.star.wanandroid.R;
import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.base.presenter.BasePresenter;
import com.star.wanandroid.contract.main.ArticleDetailContract;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.star.wanandroid.utils.RxUtils;
import com.star.wanandroid.widget.BaseObserver;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;


/**
 * @author quchao
 * @date 2018/2/13
 */

public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }

    @Override
    public void addCollectArticle(int articleId) {
        addSubscribe(mDataManager.addCollectArticle(articleId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showCollectArticleData(feedArticleListData);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(int articleId) {
        addSubscribe(mDataManager.cancelCollectArticle(articleId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.cancel_collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showCancelCollectArticleData(feedArticleListData);
                    }
                }));
    }

    @Override
    public void cancelCollectPageArticle(int articleId) {
        addSubscribe(mDataManager.cancelCollectPageArticle(articleId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.cancel_collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showCancelCollectArticleData(feedArticleListData);
                    }
                }));
    }

    @Override
    public void shareEventPermissionVerify(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        mView.shareEvent();
                    } else {
                        mView.shareError();
                    }
                }));
    }


}
