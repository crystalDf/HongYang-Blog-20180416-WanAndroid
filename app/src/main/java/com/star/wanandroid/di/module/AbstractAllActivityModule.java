package com.star.wanandroid.di.module;

import com.star.wanandroid.di.component.BaseActivityComponent;
import com.star.wanandroid.ui.hierarchy.activity.KnowledgeHierarchyDetailActivity;
import com.star.wanandroid.ui.main.activity.AboutUsActivity;
import com.star.wanandroid.ui.main.activity.ArticleDetailActivity;
import com.star.wanandroid.ui.main.activity.LoginActivity;
import com.star.wanandroid.ui.main.activity.MainActivity;
import com.star.wanandroid.ui.main.activity.SearchListActivity;
import com.star.wanandroid.ui.main.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributesSplashActivityInjector();

    @ContributesAndroidInjector(modules = ArticleDetailActivityModule.class)
    abstract ArticleDetailActivity contributesArticleDetailActivityInjector();

    @ContributesAndroidInjector(modules = KnowledgeHierarchyDetailActivityModule.class)
    abstract KnowledgeHierarchyDetailActivity contributesKnowledgeHierarchyDetailActivityInjector();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInjector();

    @ContributesAndroidInjector(modules = AboutUsActivityModule.class)
    abstract AboutUsActivity contributesAboutUsActivityInjector();

    @ContributesAndroidInjector(modules = SearchListActivityModule.class)
    abstract SearchListActivity contributesSearchListActivityInjector();

}
