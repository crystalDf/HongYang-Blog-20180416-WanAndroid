package com.star.wanandroid.di.module;

import com.star.wanandroid.di.component.BaseFragmentComponent;
import com.star.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyFragment;
import com.star.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyListFragment;
import com.star.wanandroid.ui.main.activity.CollectFragment;
import com.star.wanandroid.ui.main.activity.SettingFragment;
import com.star.wanandroid.ui.mainpager.fragment.MainPagerFragment;
import com.star.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.star.wanandroid.ui.project.fragment.ProjectFragment;
import com.star.wanandroid.ui.project.fragment.ProjectListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = CollectFragmentModule.class)
    abstract CollectFragment contributesCollectFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeFragmentModule.class)
    abstract KnowledgeHierarchyFragment contributesKnowledgeHierarchyFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeListFragmentModule.class)
    abstract KnowledgeHierarchyListFragment contributesKnowledgeHierarchyListFragmentInject();

    @ContributesAndroidInjector(modules = MainPagerFragmentModule.class)
    abstract MainPagerFragment contributesMainPagerFragmentInject();

    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributesNavigationFragmentInject();

    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributesProjectFragmentInject();

    @ContributesAndroidInjector(modules = ProjectListFragmentModule.class)
    abstract ProjectListFragment contributesProjectListFragmentInject();

    @ContributesAndroidInjector(modules = SettingFragmentModule.class)
    abstract SettingFragment contributesSettingFragmentInject();

}
