package com.star.wanandroid.ui.main.activity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.base.fragment.AbstractRootFragment;
import com.star.wanandroid.contract.main.CollectContract;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.star.wanandroid.presenter.main.CollectPresenter;
import com.star.wanandroid.ui.mainpager.adapter.ArticleListAdapter;
import com.star.wanandroid.utils.CommonUtils;
import com.star.wanandroid.utils.JudgeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author quchao
 * @date 2018/2/27
 */

public class CollectFragment extends AbstractRootFragment<CollectPresenter> implements CollectContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.collect_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.collect_floating_action_btn)
    FloatingActionButton mFloatingActionButton;

    private boolean isRefresh = true;
    private int mCurrentPage;
    private List<FeedArticleData> mArticles;
    private ArticleListAdapter mAdapter;
    private ActivityOptions mOptions;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initView();
        setRefresh();
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    public void showCollectList(FeedArticleListData feedArticleListData) {
        if (mAdapter == null) {
            return;
        }
        mArticles = feedArticleListData.getDatas();
        if (isRefresh) {
            mAdapter.replaceData(mArticles);
        } else {
            if (mArticles.size() > 0) {
                mArticles.addAll(feedArticleListData.getDatas());
                mAdapter.addData(feedArticleListData.getDatas());
            } else {
                if (mAdapter.getData().size() != 0) {
                    CommonUtils.showMessage(_mActivity, getString(R.string.load_more_no_data));
                }
            }
        }
        if (mAdapter.getData().size() == 0) {
            CommonUtils.showSnackMessage(_mActivity, getString(R.string.no_collect));
        }
        showNormal();
    }

    @Override
    public void showCancelCollectPageArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData) {
        mAdapter.remove(position);
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.cancel_collect_success));
    }

    @Override
    public void showRefreshEvent() {
        if (isVisible()) {
            mCurrentPage = 0;
            isRefresh = true;
            mPresenter.getCollectList(mCurrentPage, false);
        }
    }

    @OnClick({R.id.collect_floating_action_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_floating_action_btn:
                mRecyclerView.smoothScrollToPosition(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void reload() {
        mRefreshLayout.autoRefresh();
    }

    public static CollectFragment getInstance(String param1, String param2) {
        CollectFragment fragment = new CollectFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        mArticles = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_search_pager, mArticles);
        mAdapter.isCollectPage();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
                return;
            }
            mOptions = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
            JudgeUtils.startArticleDetailActivity(_mActivity, mOptions,
                    mAdapter.getData().get(position).getId(),
                    mAdapter.getData().get(position).getTitle(),
                    mAdapter.getData().get(position).getLink(),
                    true,
                    true,
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
                            mAdapter.getData().get(position).getChapterName(),
                            mAdapter.getData().get(position).getChapterName(),
                            mAdapter.getData().get(position).getChapterId());
                    break;
                case R.id.item_search_pager_like_iv:
                    //取消收藏
                    if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
                        return;
                    }
                    mPresenter.cancelCollectPageArticle(position, mAdapter.getData().get(position));
                    break;
                default:
                    break;
            }

        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mPresenter.getCollectList(mCurrentPage, true);
    }

    private void setRefresh() {
        mRefreshLayout.setPrimaryColorsId(Constants.BLUE_THEME, R.color.white);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            showRefreshEvent();
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mCurrentPage++;
            isRefresh = false;
            mPresenter.getCollectList(mCurrentPage, false);
            refreshLayout.finishLoadMore(1000);
        });
    }

}
