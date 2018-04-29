package com.star.wanandroid.ui.project.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.base.fragment.AbstractRootFragment;
import com.star.wanandroid.base.fragment.BaseFragment;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.project.ProjectContract;
import com.star.wanandroid.core.bean.project.ProjectClassifyData;
import com.star.wanandroid.core.event.JumpToTheTopEvent;
import com.star.wanandroid.presenter.project.ProjectPresenter;
import com.star.wanandroid.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author quchao
 * @date 2018/2/11
 */

public class ProjectFragment extends AbstractRootFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.project_divider)
    View mDivider;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;

    private List<ProjectClassifyData> mData;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private int currentPage;

    @Override
    public void onDestroyView() {
        mPresenter.setProjectCurrentPage(currentPage);
        super.onDestroyView();
    }

    public static ProjectFragment getInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getProjectClassifyData();
        currentPage = mPresenter.getProjectCurrentPage();
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList) {
        if (mPresenter.getCurrentPage() == Constants.TYPE_PROJECT) {
            mTabLayout.setVisibility(View.VISIBLE);
            mDivider.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
        } else {
            mTabLayout.setVisibility(View.INVISIBLE);
            mDivider.setVisibility(View.INVISIBLE);
            mViewPager.setVisibility(View.INVISIBLE);
        }
        mData = projectClassifyDataList;
        for (ProjectClassifyData data : mData) {
            ProjectListFragment projectListFragment = ProjectListFragment.getInstance(data.getId(), null);
            mFragments.add(projectListFragment);
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mData == null? 0 : mData.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mData.get(position).getName();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(Constants.TAB_ONE);
        showNormal();
    }

    @Override
    public void showError() {
        mTabLayout.setVisibility(View.INVISIBLE);
        mDivider.setVisibility(View.INVISIBLE);
        mViewPager.setVisibility(View.INVISIBLE);
        super.showError();
    }

    @Override
    public void reload() {
        if (mPresenter != null && mTabLayout.getVisibility() == View.INVISIBLE) {
            mPresenter.getProjectClassifyData();
        }
    }

    public void jumpToTheTop() {
        if (mFragments != null) {
            RxBus.getDefault().post(new JumpToTheTopEvent());
        }
    }

}
