package com.star.wanandroid.ui.hierarchy.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.base.fragment.AbstractRootFragment;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.hierarchy.KnowledgeHierarchyListContract;
import com.star.wanandroid.core.bean.main.collect.FeedArticleData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.star.wanandroid.core.event.CollectEvent;
import com.star.wanandroid.core.event.SwitchNavigationEvent;
import com.star.wanandroid.core.event.SwitchProjectEvent;
import com.star.wanandroid.presenter.hierarchy.KnowledgeHierarchyListPresenter;
import com.star.wanandroid.ui.main.activity.LoginActivity;
import com.star.wanandroid.ui.mainpager.adapter.ArticleListAdapter;
import com.star.wanandroid.utils.CommonUtils;
import com.star.wanandroid.utils.JudgeUtils;

import java.util.List;

import butterknife.BindView;


public class KnowledgeHierarchyListFragment extends AbstractRootFragment<KnowledgeHierarchyListPresenter>
        implements KnowledgeHierarchyListContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.knowledge_hierarchy_list_recycler_view)
    RecyclerView mRecyclerView;

    private int id;
    private int mCurrentPage;
    private List<FeedArticleData> mArticles;
    private ArticleListAdapter mAdapter;
    private boolean isRefresh = true;
    private int articlePosition;
    private ActivityOptions mOptions;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy_list;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        isInnerFragment = true;
        setRefresh();
        Bundle bundle = getArguments();
        id = bundle.getInt(Constants.ARG_PARAM1, 0);
        if (id == 0) {
            return;
        }
        //重置当前页数，防止页面切换后当前页数为较大而加载后面的数据或没有数据
        mCurrentPage = 0;
        mPresenter.getKnowledgeHierarchyDetailData(mCurrentPage, id, true);
        mAdapter = new ArticleListAdapter(R.layout.item_search_pager, mArticles);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
                return;
            }
            articlePosition = position;
            mOptions = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
            JudgeUtils.startArticleDetailActivity(_mActivity,
                    mOptions,
                    mAdapter.getData().get(position).getId(),
                    mAdapter.getData().get(position).getTitle().trim(),
                    mAdapter.getData().get(position).getLink().trim(),
                    mAdapter.getData().get(position).isCollect(),
                    false,
                    false);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.item_search_pager_chapterName:
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
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    public void showKnowledgeHierarchyDetailData(FeedArticleListData feedArticleListData) {
        mArticles = feedArticleListData.getDatas();
        if (isRefresh) {
            mAdapter.replaceData(mArticles);
        } else {
            if (mArticles.size() > 0) {
                mAdapter.addData(mArticles);
            } else {
                CommonUtils.showMessage(_mActivity, getString(R.string.load_more_no_data));
            }
        }
        showNormal();
    }

    @Override
    public void reload() {
        if (mPresenter != null) {
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void showCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData) {
        mAdapter.setData(position, feedArticleData);
        RxBus.getDefault().post(new CollectEvent(false));
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.collect_success));
    }

    @Override
    public void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData) {
        mAdapter.setData(position, feedArticleData);
        RxBus.getDefault().post(new CollectEvent(true));
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.cancel_collect_success));
    }

    @Override
    public void showJumpTheTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showReloadDetailEvent() {
        if (mRefreshLayout != null) {
            mRefreshLayout.autoRefresh();
        }
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

    public static KnowledgeHierarchyListFragment getInstance(int id, String param2) {
        KnowledgeHierarchyListFragment fragment = new KnowledgeHierarchyListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_PARAM1, id);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

    private void setRefresh() {
        mRefreshLayout.setPrimaryColorsId(Constants.BLUE_THEME, R.color.white);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mCurrentPage = 0;
            if (id != 0) {
                isRefresh = true;
                mPresenter.getKnowledgeHierarchyDetailData(0, id, false);
            }
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mCurrentPage++;
            if (id != 0) {
                isRefresh = false;
                mPresenter.getKnowledgeHierarchyDetailData(mCurrentPage, id, false);
            }
            refreshLayout.finishLoadMore(1000);
        });
    }
}
