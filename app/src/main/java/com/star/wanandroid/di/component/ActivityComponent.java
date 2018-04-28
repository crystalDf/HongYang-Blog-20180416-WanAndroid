package com.star.wanandroid.di.component;

import android.app.Activity;

import com.star.wanandroid.ui.hierarchy.activity.KnowledgeHierarchyDetailActivity;
import com.star.wanandroid.ui.main.activity.AboutUsActivity;
import com.star.wanandroid.ui.main.activity.ArticleDetailActivity;
import com.star.wanandroid.ui.main.activity.LoginActivity;
import com.star.wanandroid.ui.main.activity.MainActivity;
import com.star.wanandroid.ui.main.activity.SearchListActivity;
import com.star.wanandroid.ui.main.activity.SplashActivity;

import dagger.Component;
import json.chao.com.wanandroid.di.module.ActivityModule;
import json.chao.com.wanandroid.di.scope.ActivityScope;


/**
 * @author quchao
 * @date 2017/11/27
 */

@ActivityScope
@Component(dependencies = json.chao.com.wanandroid.di.component.AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    /**
     * 获取Activity实例
     *
     * @return Activity
     */
    Activity getActivity();

    /**
     * 注入MAinActivity所需的依赖
     *
     * @param mainActivity MainActivity
     */
    void inject(MainActivity mainActivity);

    /**
     * 注入SplashActivity所需的依赖
     *
     * @param splashActivity SplashActivity
     */
    void inject(SplashActivity splashActivity);

    /**
     * 注入ArticleDetailActivity所需的依赖
     *
     * @param articleDetailActivity ArticleDetailActivity
     */
    void inject(ArticleDetailActivity articleDetailActivity);

    /**
     * 注入KnowledgeHierarchyDetailActivity所需的依赖
     *
     * @param knowledgeHierarchyDetailActivity KnowledgeHierarchyDetailActivity
     */
    void inject(KnowledgeHierarchyDetailActivity knowledgeHierarchyDetailActivity);

    /**
     * 注入LoginActivity所需的依赖
     *
     * @param loginActivity LoginActivity
     */
    void inject(LoginActivity loginActivity);

    /**
     * 注入AboutUsActivity所需的依赖
     *
     * @param aboutUsActivity AboutUsActivity
     */
    void inject(AboutUsActivity aboutUsActivity);

    /**
     * 注入SearchListActivity所需的依赖
     *
     * @param searchListActivity SearchListActivity
     */
    void inject(SearchListActivity searchListActivity);

}
