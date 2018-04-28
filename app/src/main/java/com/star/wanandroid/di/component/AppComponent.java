package com.star.wanandroid.di.component;


import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.di.module.AppModule;
import com.star.wanandroid.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author quchao
 * @date 2017/11/27
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

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
