package com.star.wanandroid.di.component;


import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.di.module.AbstractAllActivityModule;
import com.star.wanandroid.di.module.AbstractAllDialogFragmentModule;
import com.star.wanandroid.di.module.AbstractAllFragmentModule;
import com.star.wanandroid.di.module.AppModule;
import com.star.wanandroid.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author quchao
 * @date 2017/11/27
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AbstractAllDialogFragmentModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    /**
     * 注入WanAndroidApp实例
     *
     * @param wanAndroidApp WanAndroidApp
     */
    void inject(WanAndroidApp wanAndroidApp);

    /**
     * 提供App的Context
     *
     * @return GeeksApp context
     */
    WanAndroidApp getContext();

    /**
     * 数据中心
     *
     * @return DataManager
     */
    DataManager getDataManager();

}
