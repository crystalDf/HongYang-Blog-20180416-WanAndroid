package com.star.wanandroid.di.module;


import com.star.wanandroid.app.WanAndroidApp;
import com.star.wanandroid.core.DataManager;
import com.star.wanandroid.core.db.DbHelper;
import com.star.wanandroid.core.db.GreenDaoHelper;
import com.star.wanandroid.core.http.HttpHelper;
import com.star.wanandroid.core.http.RetrofitHelper;
import com.star.wanandroid.core.prefs.PreferenceHelper;
import com.star.wanandroid.core.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author quchao
 * @date 2017/11/27
 */

@Module
public class AppModule {

    private final WanAndroidApp application;

    public AppModule(WanAndroidApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    WanAndroidApp provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDBHelper(GreenDaoHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DbHelper dbhelper, PreferenceHelper preferencesHelper) {
        return new DataManager(httpHelper, dbhelper, preferencesHelper);
    }

}
