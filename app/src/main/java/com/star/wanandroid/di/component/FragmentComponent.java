package com.star.wanandroid.di.component;

import android.app.Activity;

import com.star.wanandroid.di.module.FragmentModule;
import com.star.wanandroid.di.scope.FragmentScope;
import com.star.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyFragment;
import com.star.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyListFragment;
import com.star.wanandroid.ui.main.activity.CollectFragment;
import com.star.wanandroid.ui.main.activity.SettingFragment;
import com.star.wanandroid.ui.main.fragment.SearchDialogFragment;
import com.star.wanandroid.ui.main.fragment.UsageDialogFragment;
import com.star.wanandroid.ui.mainpager.fragment.MainPagerFragment;
import com.star.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.star.wanandroid.ui.project.fragment.ProjectFragment;
import com.star.wanandroid.ui.project.fragment.ProjectListFragment;

import dagger.Component;

/**
 * @author quchao
 * @date 2017/11/27
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    /**
     * 获取Activity实例
     *
     * @return Activity
     */
    Activity getActivity();

    /**
     * 注入MainPagerFragment所需的依赖
     *
     * @param mainPagerFragment MainPagerFragment
     */
    void inject(MainPagerFragment mainPagerFragment);

    /**
     * 注入KnowledgeHierarchyFragment所需的依赖
     *
     * @param knowledgeHierarchyFragment KnowledgeHierarchyFragment
     */
    void inject(KnowledgeHierarchyFragment knowledgeHierarchyFragment);

    /**
     * 注入KnowledgeHierarchyListFragment所需的依赖
     *
     * @param knowledgeHierarchyListFragment KnowledgeHierarchyListFragment
     */
    void inject(KnowledgeHierarchyListFragment knowledgeHierarchyListFragment);

    /**
     * 注入VideoFrequencyFragment所需的依赖
     *
     * @param projectFragment ProjectFragment
     */
    void inject(ProjectFragment projectFragment);

    /**
     * 注入NewsFragment所需的依赖
     *
     * @param navigationFragment NavigationFragment
     */
    void inject(NavigationFragment navigationFragment);

    /**
     * 注入ProjectListFragment所需的依赖
     *
     * @param projectListFragment ProjectListFragment
     */
    void inject(ProjectListFragment projectListFragment);

    /**
     * 注入ProjectListFragment所需的依赖
     *
     * @param searchDialogFragment SearchDialogFragment
     */
    void inject(SearchDialogFragment searchDialogFragment);

    /**
     * 注入UsageDialogFragment所需的依赖
     *
     * @param usageDialogFragment UsageDialogFragment
     */
    void inject(UsageDialogFragment usageDialogFragment);

    /**
     * 注入CollectFragment所需的依赖
     *
     * @param collectFragment CollectFragment
     */
    void inject(CollectFragment collectFragment);

    /**
     * 注入SettingFragment所需的依赖
     *
     * @param settingFragment SettingFragment
     */
    void inject(SettingFragment settingFragment);

}
