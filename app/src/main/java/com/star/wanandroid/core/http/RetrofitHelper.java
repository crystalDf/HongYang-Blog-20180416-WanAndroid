package com.star.wanandroid.core.http;

import com.star.wanandroid.core.bean.BaseResponse;
import com.star.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import com.star.wanandroid.core.bean.main.banner.BannerData;
import com.star.wanandroid.core.bean.main.collect.FeedArticleListData;
import com.star.wanandroid.core.bean.main.login.LoginData;
import com.star.wanandroid.core.bean.main.search.TopSearchData;
import com.star.wanandroid.core.bean.main.search.UsefulSiteData;
import com.star.wanandroid.core.bean.navigation.NavigationListData;
import com.star.wanandroid.core.bean.project.ProjectClassifyData;
import com.star.wanandroid.core.bean.project.ProjectListData;
import com.star.wanandroid.core.http.api.GeeksApis;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RetrofitHelper implements HttpHelper {

    private GeeksApis mGeeksApis;

    @Inject
    RetrofitHelper(GeeksApis geeksApis) {
        mGeeksApis = geeksApis;
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mGeeksApis.getFeedArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int pageNum, String k) {
        return mGeeksApis.getSearchList(pageNum, k);
    }

    @Override
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return mGeeksApis.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites() {
        return mGeeksApis.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mGeeksApis.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page, int cid) {
        return mGeeksApis.getKnowledgeHierarchyDetailData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mGeeksApis.getNavigationListData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mGeeksApis.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid) {
        return mGeeksApis.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mGeeksApis.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mGeeksApis.getRegisterData(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id) {
        return mGeeksApis.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String title, String author, String link) {
        return mGeeksApis.addCollectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page) {
        return mGeeksApis.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id) {
        return mGeeksApis.cancelCollectPageArticle(id, -1);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id) {
        return mGeeksApis.cancelCollectArticle(id, -1);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mGeeksApis.getBannerData();
    }


}
