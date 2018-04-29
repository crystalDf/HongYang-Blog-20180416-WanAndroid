package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;
import com.star.wanandroid.core.bean.main.search.TopSearchData;
import com.star.wanandroid.core.dao.HistoryData;

import java.util.List;

public interface SearchContract {

    interface View extends BaseView {

        /**
         * Show history data
         *
         * @param historyDataList List<HistoryData>
         */
        void showHistoryData(List<HistoryData> historyDataList);

        /**
         * Show top search data
         *
         * @param topSearchDataList List<TopSearchData>
         */
        void showTopSearchData(List<TopSearchData> topSearchDataList);

        /**
         * Judge to the search list activity
         */
        void judgeToTheSearchListActivity();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Load all history data
         *
         * @return all history data
         */
        List<HistoryData> loadAllHistoryData();

        /**
         * Add history data
         *
         * @param data history data
         */
        void addHistoryData(String data);

        /**
         * 热搜
         */
        void getTopSearchData();

        /**
         * Clear history data
         */
        void clearHistoryData();
    }

}
