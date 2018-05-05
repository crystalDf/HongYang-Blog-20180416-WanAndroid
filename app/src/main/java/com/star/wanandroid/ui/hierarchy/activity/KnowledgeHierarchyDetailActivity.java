package com.star.wanandroid.ui.hierarchy.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.base.activity.BaseActivity;
import com.star.wanandroid.base.fragment.BaseFragment;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.hierarchy.KnowledgeHierarchyDetailContract;
import com.star.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import com.star.wanandroid.core.event.KnowledgeJumpTopEvent;
import com.star.wanandroid.presenter.hierarchy.KnowledgeHierarchyDetailPresenter;
import com.star.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyListFragment;
import com.star.wanandroid.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author quchao
 * @date 2018/2/23
 */

public class KnowledgeHierarchyDetailActivity extends BaseActivity<KnowledgeHierarchyDetailPresenter>
        implements KnowledgeHierarchyDetailContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.knowledge_hierarchy_detail_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.knowledge_hierarchy_detail_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.knowledge_floating_action_btn)
    FloatingActionButton mFloatingActionButton;

    private List<KnowledgeHierarchyData> knowledgeHierarchyDataList;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private String chapterName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_hierarchy_detail;
    }

    @Override
    protected void initEventAndData() {
        initToolbar();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments == null? 0 : mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (getIntent().getBooleanExtra(Constants.IS_SINGLE_CHAPTER, false)) {
                    return chapterName;
                } else {
                    return knowledgeHierarchyDataList.get(position).getName();
                }
            }
        });
        mTabLayout.setViewPager(mViewPager);
    }

    @OnClick({R.id.knowledge_floating_action_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.knowledge_floating_action_btn:
                RxBus.getDefault().post(new KnowledgeJumpTopEvent());
                break;
            default:
                break;
        }
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        if (getIntent().getBooleanExtra(Constants.IS_SINGLE_CHAPTER, false)) {
            String superChapterName = getIntent().getStringExtra(Constants.SUPER_CHAPTER_NAME);
            chapterName = getIntent().getStringExtra(Constants.CHAPTER_NAME);
            int chapterId = getIntent().getIntExtra(Constants.CHAPTER_ID, 0);
            mTitleTv.setText(superChapterName);
            mFragments.add(KnowledgeHierarchyListFragment.getInstance(chapterId, null));
        } else {
            KnowledgeHierarchyData knowledgeHierarchyData = (KnowledgeHierarchyData) getIntent().getSerializableExtra(Constants.ARG_PARAM1);
            assert knowledgeHierarchyData != null && knowledgeHierarchyData.getName() != null;
            mTitleTv.setText(knowledgeHierarchyData.getName().trim());
            knowledgeHierarchyDataList = knowledgeHierarchyData.getChildren();
            if (knowledgeHierarchyDataList == null) {
                return;
            }
            for (KnowledgeHierarchyData data : knowledgeHierarchyDataList) {
                mFragments.add(KnowledgeHierarchyListFragment.getInstance(data.getId(), null));
            }
        }
    }

    @Override
    public void showSwitchProject() {
        onBackPressedSupport();
    }

    @Override
    public void showSwitchNavigation() {
        onBackPressedSupport();
    }


}
