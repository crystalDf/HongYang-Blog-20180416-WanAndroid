package com.star.wanandroid.ui.mainpager.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.base.fragment.AbstractRootFragment;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.mainpager.MainPagerContract;
import com.star.wanandroid.core.bean.main.banner.BannerData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.star.wanandroid.core.event.AutoLoginEvent;
import com.star.wanandroid.core.event.LoginEvent;
import com.star.wanandroid.core.event.SwitchNavigationEvent;
import com.star.wanandroid.core.event.SwitchProjectEvent;
import com.star.wanandroid.core.http.cookies.CookiesManager;
import com.star.wanandroid.presenter.mainpager.MainPagerPresenter;
import com.star.wanandroid.ui.main.activity.LoginActivity;
import com.star.wanandroid.ui.mainpager.adapter.ArticleListAdapter;
import com.star.wanandroid.utils.CommonUtils;
import com.star.wanandroid.utils.GlideImageLoader;
import com.star.wanandroid.utils.JudgeUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author quchao
 * @date 2017/11/29
 */

public class MainPagerFragment extends AbstractRootFragment<MainPagerPresenter> implements MainPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.main_pager_recycler_view)
    RecyclerView mRecyclerView;

    private List<FeedArticleData> mFeedArticleDataList;
    private ArticleListAdapter mAdapter;

    private int articlePosition;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private Banner mBanner;
    private boolean isRecreate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isRecreate = getArguments().getBoolean(Constants.ARG_PARAM1);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    public static MainPagerFragment getInstance(boolean param1, String param2) {
        MainPagerFragment fragment = new MainPagerFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mFeedArticleDataList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_search_pager, mFeedArticleDataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
                return;
            }
            //记录点击的文章位置，便于在文章内点击收藏返回到此界面时能展示正确的收藏状态
            articlePosition = position;
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
            JudgeUtils.startArticleDetailActivity(_mActivity,
                    options,
                    mAdapter.getData().get(position).getId(),
                    mAdapter.getData().get(position).getTitle(),
                    mAdapter.getData().get(position).getLink(),
                    mAdapter.getData().get(position).isCollect(),
                    false,
                    false);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.item_search_pager_chapterName:
                    if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
                        return;
                    }
                    JudgeUtils.startKnowledgeHierarchyDetailActivity(_mActivity,
                            true,
                            mAdapter.getData().get(position).getSuperChapterName(),
                            mAdapter.getData().get(position).getChapterName(),
                            mAdapter.getData().get(position).getChapterId());
                    break;
                case R.id.item_search_pager_like_iv:
                    likeEvent(position);
                    break;
                case R.id.item_search_pager_tag_red_tv:
                    if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
                        return;
                    }
                    String superChapterName = mAdapter.getData().get(position).getSuperChapterName();
                    if (superChapterName.contains(getString(R.string.open_project))) {
                        RxBus.getDefault().post(new SwitchProjectEvent());
                    } else if (superChapterName.contains(getString(R.string.navigation))) {
                        RxBus.getDefault().post(new SwitchNavigationEvent());
                    }
                    break;
                default:
                    break;
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        //add head banner
        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.head_banner, null));
        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
        mHeaderGroup.removeView(mBanner);
        mAdapter.addHeaderView(mBanner);
        mRecyclerView.setAdapter(mAdapter);

        setRefresh();
        if (!TextUtils.isEmpty(mPresenter.getLoginAccount())
                && !TextUtils.isEmpty(mPresenter.getLoginPassword())
                && !isRecreate) {
            mPresenter.loadMainPagerData();
        } else {
            mPresenter.autoRefresh();
        }
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    public void showAutoLoginSuccess() {
        if (isAdded()) {
            CommonUtils.showSnackMessage(_mActivity, getString(R.string.auto_login_success));
            RxBus.getDefault().post(new AutoLoginEvent());
        }
    }

    @Override
    public void showAutoLoginFail() {
        mPresenter.setLoginStatus(false);
        CookiesManager.clearAllCookies();
        RxBus.getDefault().post(new LoginEvent(false));
    }

    @Override
    public void showArticleList(FeedArticleListData feedArticleListData, boolean isRefresh) {
        if (mPresenter.getCurrentPage() == Constants.TYPE_MAIN_PAGER) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {

            mRecyclerView.setVisibility(View.INVISIBLE);
        }
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            mFeedArticleDataList = feedArticleListData.getDatas();
            mAdapter.replaceData(feedArticleListData.getDatas());
        } else {
            mFeedArticleDataList.addAll(feedArticleListData.getDatas());
            mAdapter.addData(feedArticleListData.getDatas());
        }
        showNormal();
    }

    @Override
    public void showCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData) {
        mAdapter.setData(position, feedArticleData);
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.collect_success));
    }

    @Override
    public void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData) {
        mAdapter.setData(position, feedArticleData);
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.cancel_collect_success));
    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(bannerDataList.size() * 400);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        mBanner.setOnBannerListener(i -> JudgeUtils.startArticleDetailActivity(_mActivity, null,
                0, mBannerTitleList.get(i), mBannerUrlList.get(i),
                false, false, true));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void showLoginView() {
        mPresenter.getFeedArticleList();
    }

    @Override
    public void showLogoutView() {
        mPresenter.getFeedArticleList();
    }

    @Override
    public void showCollectSuccess() {
        if (mAdapter != null && mAdapter.getData().size() > articlePosition) {
            mAdapter.getData().get(articlePosition).setCollect(true);
            mAdapter.setData(articlePosition, mAdapter.getData().get(articlePosition));
        }
    }

    @Override
    public void showCancelCollectSuccess() {
        if (mAdapter != null && mAdapter.getData().size() > articlePosition) {
            mAdapter.getData().get(articlePosition).setCollect(false);
            mAdapter.setData(articlePosition, mAdapter.getData().get(articlePosition));
        }
    }

    @Override
    public void showError() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        super.showError();
    }

    @Override
    public void reload() {
        if (mRefreshLayout != null && mPresenter != null
                && mRecyclerView.getVisibility() == View.INVISIBLE
                && CommonUtils.isNetworkConnected()) {
            mRefreshLayout.autoRefresh();
        }
    }

    private void likeEvent(int position) {
        if (!mPresenter.getLoginStatus()) {
            startActivity(new Intent(_mActivity, LoginActivity.class));
            CommonUtils.showMessage(_mActivity, getString(R.string.login_tint));
            return;
        }
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }
        if (mAdapter.getData().get(position).isCollect()) {
            mPresenter.cancelCollectArticle(position, mAdapter.getData().get(position));
        } else {
            mPresenter.addCollectArticle(position, mAdapter.getData().get(position));
        }
    }

    public void jumpToTheTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh();
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }
}
